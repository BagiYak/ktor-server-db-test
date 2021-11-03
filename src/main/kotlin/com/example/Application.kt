import com.example.plugins.configureHTTP
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.application.*
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.select
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.timestamp
import org.ktorm.schema.varchar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    // to open in browsers
    configureHTTP()

    // to accept application/json contentType request via http
    configureSerialization()

    configureRouting()

    // Second variant how to connect to DB Postgresql - using framework Ktorm.org
    val db_ktorm = Database.connect("jdbc:postgresql://localhost:5432/test_db", user = "postgres", password = "postgres")
    println("db_ktorm: ${db_ktorm.name}")

    // insert to table
    db_ktorm.insert(Users) {
        set(it.email, "test@test.kz")
        set(it.password, "password")
        set(it.firstname, "testName")
        set(it.lastname, "testLastname")
        set(it.picture_url, "picture_url")
        set(it.phone, +77072201010)
    }

    // get all from table
    for (row in db_ktorm.from(Users).select()) {
        println()
        println("email: " + row[Users.email])
        println("password: " + row[Users.password])
        println("firstname: " + row[Users.firstname])
        println("lastname: " + row[Users.lastname])
        println("picture_url: " + row[Users.picture_url])
        println("phone: " + row[Users.phone])
        println( "date_created utc-0: " + row[Users.date_created] )
        println( "date_created toEpochMilli: " + row[Users.date_created]?.toEpochMilli() )
        println( "local date now: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) )
        println("date_last_logged_in utc-0: " + row[Users.date_last_logged_in])
        println()
    }

}


// create Kotlin objects to describe your table schemas
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
