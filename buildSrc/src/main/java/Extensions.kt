import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addNetworkDependencies() {
    add("implementation", Deps.retrofit)
    add("implementation", Deps.kotlinSerialization)
    add("implementation", Deps.retrofitSerialization)
    add("implementation", Deps.okhttpLogger)
}
fun DependencyHandlerScope.addServiceLocatorDependencies() {
    add("implementation", Deps.Koin.core)
    add("implementation", Deps.Koin.android)
    add("implementation", Deps.Koin.navigation)
}

fun DependencyHandlerScope.addNavigationDependencies() {
    add("implementation", Deps.Navigation.ui)
    add("implementation", Deps.Navigation.fragment)
    
}
fun DependencyHandlerScope.addTestDependencies() {
    add("testImplementation", Deps.Test.junit)
    add("testImplementation", Deps.Test.coroutineTest)
    add("testImplementation", Deps.Test.mockK)

}