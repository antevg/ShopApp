import UIKit
import SwiftUI
import ComposeApp
import Firebase

@main
struct iosApp: App {

    init(){
        FirebaseApp.configure()
        KoinHelperKt.doInitKoin()
      //  let router: Router = SharedModulesKt.getModules()
      }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView().ignoresSafeArea(.all)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
