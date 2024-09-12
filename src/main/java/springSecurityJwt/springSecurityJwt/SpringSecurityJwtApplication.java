package springSecurityJwt.springSecurityJwt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData() {
		return args -> {
			String url = "jdbc:h2:mem:testdb"; // H2 인메모리 데이터베이스 URL
			String user = "sa";
			String password = "";

			// 삽입할 SQL 쿼리들
			String[] sqlScripts = {
					"INSERT INTO USERS (USER_ID, USERNAME, PASSWORD, NICKNAME, ACTIVATED) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);",
					"INSERT INTO AUTHORITY (AUTHORITY_NAME) VALUES ('ROLE_USER');",
					"INSERT INTO AUTHORITY (AUTHORITY_NAME) VALUES ('ROLE_ADMIN');",
					"INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_USER');",
					"INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');"
			};

			try (Connection conn = DriverManager.getConnection(url, user, password);
				 Statement stmt = conn.createStatement()) {

				// 트랜잭션 시작
				conn.setAutoCommit(false);

				// 각 SQL 구문을 배치에 추가
				for (String sql : sqlScripts) {
					stmt.addBatch(sql);
				}

				// 배치 실행
				stmt.executeBatch();

				// 트랜잭션 커밋
				conn.commit();
				System.out.println("SQL script executed successfully!");

			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

//	@Bean
//	public CommandLineRunner loadData() {
//		return args -> {
//			String url = "jdbc:h2:mem:testdb"; // H2 인메모리 데이터베이스 URL
//			String user = "sa";
//			String password = "";
//
//			String sqlScript = """
//					    INSERT INTO USERS (USER_ID, USERNAME, PASSWORD, NICKNAME, ACTIVATED)
//					    VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
//
//					    INSERT INTO AUTHORITY (AUTHORITY_NAME) VALUES ('ROLE_USER');
//					    INSERT INTO AUTHORITY (AUTHORITY_NAME) VALUES ('ROLE_ADMIN');
//
//					    INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_USER');
//					    INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');
//					""";
//
//			try (Connection conn = DriverManager.getConnection(url, user, password);
//				 Statement stmt = conn.createStatement()) {
//
//				stmt.execute(sqlScript);
//				System.out.println("SQL script executed successfully!");
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		};
//	}
}
