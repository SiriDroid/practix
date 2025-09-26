import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.timidgiraffe.practix.AppDatabase
import com.timidgiraffe.practix.data.PracticeRepositoryImpl
import com.timidgiraffe.practix.domain.PracticeRepository
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Platform module is provided by each platform. On iOS, you’ll create it in iosMain
 * and provide the actuals (like creating the SQLDelight driver).
 */
actual fun platformModule(): Module  = module {
    single { DatabaseDriverFactory() }
    single<NativeSqliteDriver> { get<DatabaseDriverFactory>().createDriver() as NativeSqliteDriver }
    single { AppDatabase(get()) }
    single<PracticeRepository> { PracticeRepositoryImpl(get()) }
}