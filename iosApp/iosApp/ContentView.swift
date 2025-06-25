import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        let controller = MainViewControllerKt.MainViewController()

        // Configurar el color de fondo de la ventana principal
        controller.view?.backgroundColor = .black

        // Configurar el color de la barra de estado
        let appearance = UINavigationBarAppearance()
        appearance.configureWithOpaqueBackground()
        appearance.backgroundColor = .black

        UINavigationBar.appearance().standardAppearance = appearance
        UINavigationBar.appearance().scrollEdgeAppearance = appearance

        // Configurar el color de la barra de pestañas
        let tabBarAppearance = UITabBarAppearance()
        tabBarAppearance.configureWithOpaqueBackground()
        tabBarAppearance.backgroundColor = .black

        UITabBar.appearance().standardAppearance = tabBarAppearance
        if #available(iOS 15.0, *) {
            UITabBar.appearance().scrollEdgeAppearance = tabBarAppearance
        }

        return controller
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ZStack {
            // Fondo negro que se extiende a las áreas seguras
            Color.black
                .ignoresSafeArea()

            // Vista de Compose
            ComposeView()
                .ignoresSafeArea(.keyboard) // Compose tiene su propio manejador de teclado
        }
    }
}



