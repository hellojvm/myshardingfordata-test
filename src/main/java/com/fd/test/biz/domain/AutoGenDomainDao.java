package com.fd.test.biz.domain;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * domain包自动创建DAO，直接拷贝到domain对应的目录执行就可以了
 * 
 * @author 符冬
 *
 */
public class AutoGenDomainDao {
	public static void main(String[] args) throws Exception {
		String absolutePath = new File("").getAbsolutePath();
		URL url = AutoGenDomainDao.class.getResource("");
		Path start2 = Paths.get(url.toURI());

		String pkg = start2.getParent().toString().split("classes[/\\\\]")[1].replaceAll("[/\\\\]", ".") + ".dao";
		String sbpath = pkg.replaceAll("[.]", "/");
		Path target = Paths.get(absolutePath, "src/main/java", sbpath);
		if (!Files.exists(target)) {
			Files.createDirectories(target);
		}
		String impldir = "impl";
		Path implpath = Paths.get(target.toString(), impldir);
		if (!Files.exists(implpath)) {
			Files.createDirectories(implpath);
		}
		Files.walkFileTree(start2, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				String fname = file.getFileName().toString();
				if (!fname.contains("$") && !fname.contains(AutoGenDomainDao.class.getSimpleName())) {
					for (int i = 0; i < 2; i++) {
						String tmppkg = pkg;
						// 实现类
						if (i == 1) {
							tmppkg = pkg + "." + "impl";
						}

						String domain = fname.split("[.]")[0];
//					System.out.println(domain);
						String daoname = String.format("%s%s", domain, "Dao");
						String infName = String.format("%s%s", "I", daoname);
						StringBuilder cb = new StringBuilder(String.format("package %s;", tmppkg));
						cb.append("\r\n");
						cb.append("\r\n");
						String dpath = file.toString().split("classes[/\\\\]")[1].replaceAll("[/\\\\]", ".");
						cb.append("import ");
						cb.append(dpath.substring(0, dpath.lastIndexOf(".")));
						cb.append(";");
						cb.append("\r\n");
						cb.append("\r\n");
						if (i == 1) {
							cb.append("import org.springframework.stereotype.Repository;");
							cb.append("\r\n");
							cb.append("\r\n");
							cb.append("import com.fd.myshardingfordata.dao.base.impl.DaoShardingBase;");
							cb.append("\r\n");
							cb.append("\r\n");
							cb.append(String.format("import %s.%s;", pkg, infName));
							cb.append("\r\n");
							cb.append("\r\n");
							cb.append("\r\n");
							cb.append("@Repository");
						} else {
							cb.append("import com.fd.myshardingfordata.dao.base.IBaseShardingDao;");
							cb.append("\r\n");
							cb.append("\r\n");
						}
						cb.append("\r\n");

						if (i == 1) {
							cb.append(String.format("public class %s extends %s<%s> implements %s {\r\n" + "\r\n" + "}",
									daoname, "DaoShardingBase", domain, infName));
						} else {
							cb.append(String.format(
									"public interface %s extends IBaseShardingDao<%s> {\r\n" + "\r\n" + "}", infName,
									domain));
						}
						String str = cb.toString();
						Path daopath = Paths.get(i == 0 ? target.toString() : target.toString() + "/" + impldir,
								String.format("%s.java", i == 0 ? infName : daoname));
						if (Files.notExists(daopath)) {
							Files.write(daopath, str.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
									StandardOpenOption.TRUNCATE_EXISTING);
							if (i == 0) {
								System.out.println("@Resource");
								System.out.println(String.format("private  %s  %s ;", infName,
										infName.substring(1, 2).toLowerCase() + infName.substring(2)));

							}
						}
					}
				}
				return super.visitFile(file, attrs);
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
