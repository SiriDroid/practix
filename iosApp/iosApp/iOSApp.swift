import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
         // Initialize Koin on app launch
         ShareModuleKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
