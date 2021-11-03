import io.ktor.application.*
import com.example.plugins.configureHTTP
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import org.ktorm.schema.*
import java.lang.Exception
import java.sql.DriverManager


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    // to open in browsers
    configureHTTP()

    // to accept application/json contentType request via http
    configureSerialization()

    configureRouting()

    // First variant how to connect to DB Postgresql - using DriverManager from package java.sql
    try {
        Class.forName("org.postgresql.Driver")
        val connection_db_DriverManager = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "postgres", "postgres")
        println("DriverManager clientInfo: ${connection_db_DriverManager.clientInfo}")
        println("DriverManager schema: ${connection_db_DriverManager.schema}")
        val preparedStatement = connection_db_DriverManager.prepareStatement("select * from users WHERE email = 'test@test.kz' OR email = 'bagdat.yakushev@tamur.kz'")
        val resultSet = preparedStatement.executeQuery()
        while(resultSet.next()) {
            println("resultSet: ${resultSet.getString("password")}")
        }
    } catch (err: Exception) {
        println("DriverManager Exception: $err")
        System.err.println(err.message)
    }

    // Second variant how to connect to DB Postgresql - using framework Ktorm.org
//    val db_ktorm = Database.connect("jdbc:postgresql://localhost:5432/test_db", user = "postgres", password = "postgres")
//    println("db_ktorm: ${db_ktorm.name}")
//    // insert to table
//    db_ktorm.insert(Users) {
//        set(it.email, "test@test.kz")
//        set(it.password, "password")
//        set(it.firstname, "testName")
//        set(it.lastname, "testLastname")
//        set(it.picture_url, "picture_url")
//        set(it.phone, +77072201010)
//    }
//    // get all from table
//    for (row in db_ktorm.from(Users).select()) {
//        println()
//        println("email: " + row[Users.email])
//        println("firstname: " + row[Users.firstname])
//        println("lastname: " + row[Users.lastname])
//        println("picture_url: " + row[Users.picture_url])
//        println("phone: " + row[Users.phone])
//        println()
//    }
}



object Users : Table<Nothing>("users") {
    val id = long("id").primaryKey()
    val email = varchar("email")
    val password = varchar("password")
    val firstname = varchar("firstname")
    val lastname = varchar("lastname")
    val picture_url = varchar("picture_url")
    val phone = long("phone")
    val date_created = timestamp("date_created")
    val date_last_logged_in = timestamp("date_last_logged_in")
}
