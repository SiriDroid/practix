import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.koinApplication

//fun shareModuleFactory() = module {
//    single { AppDatabase(get()) }
//    single<PracticeRepository> { PracticeRepositoryImpl(get()) }
//}

//// Common (platform-agnostic) module
//private val commonModule = module {
//    // DB driver is platform-specific (actual in iosMain/androidMain), but we can bind the *factory* here:
//    single<DatabaseDriverFactory> { get() }
//    single { AppDatabase(get()) }
//    single<PracticeRepository> { PracticeRepositoryImpl(get()) }
//}

/**
 * Platform module is provided by each platform. On iOS, you’ll create it in iosMain
 * and provide the actuals (like creating the SQLDelight driver).
 */
expect fun platformModule(): Module

/**
 * Call this from iOS (and Android if you want). It is safe to call once at app start.
 */
//fun initKoin(
//    extraModules: List<Module> = emptyList(),
//): Koin {
//    val app = startKoin {
//        modules(
////            listOf(platformModule()) + extraModules
//            listOf(platformModule())
//
//        )
//    }
//    return app.koin
//}

fun initKoin(): Koin {
    val app = startKoin {
        modules(
            listOf(platformModule())
        )
    }
    return app.koin
}
/** Convenience accessor for Swift (and Kotlin) */
fun getKoin(): Koin = koinApplication().koin