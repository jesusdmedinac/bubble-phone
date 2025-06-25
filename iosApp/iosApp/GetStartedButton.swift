import SwiftUI
import UIKit

// MARK: - SwiftUI Button View
struct GetStartedButtonView: View {
    let text: String
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Text(text)
                .font(.system(size: 16, weight: .semibold))
                .foregroundColor(.white)
                .padding(EdgeInsets(top: 12, leading: 24, bottom: 12, trailing: 24))
        }
        .background(Color.blue)
        .cornerRadius(8)
        .frame(height: 48)
    }
}

// MARK: - UIViewRepresentable Wrapper
struct GetStartedButtonWrapper: UIViewRepresentable {
    let text: String
    let action: () -> Void
    
    func makeUIView(context: Context) -> UIButton {
        let hostingController = UIHostingController(
            rootView: GetStartedButtonView(text: text, action: action)
        )
        
        let button = UIButton(type: .system)
        button.addTarget(context.coordinator, 
                       action: #selector(Coordinator.buttonTapped), 
                       for: .touchUpInside)
        
        // Add the SwiftUI view as a child view controller
        if let hostedView = hostingController.view {
            button.addSubview(hostedView)
            hostedView.translatesAutoresizingMaskIntoConstraints = false
            NSLayoutConstraint.activate([
                hostedView.leadingAnchor.constraint(equalTo: button.leadingAnchor),
                hostedView.trailingAnchor.constraint(equalTo: button.trailingAnchor),
                hostedView.topAnchor.constraint(equalTo: button.topAnchor),
                hostedView.bottomAnchor.constraint(equalTo: button.bottomAnchor)
            ])
        }
        
        return button
    }
    
    func updateUIView(_ uiView: UIButton, context: Context) {
        // Update the view if needed
    }
    
    func makeCoordinator() -> Coordinator {
        Coordinator(action: action)
    }
    
    class Coordinator: NSObject {
        var action: () -> Void
        
        init(action: @escaping () -> Void) {
            self.action = action
        }
        
        @objc func buttonTapped() {
            action()
        }
    }
}

// MARK: - UIView Extension for Kotlin Interop
private var handle: UInt8 = 0

extension UIView {
    private var onClickAction: (() -> Void)? {
        get {
            return objc_getAssociatedObject(self, &handle) as? () -> Void
        }
        set {
            objc_setAssociatedObject(self, &handle, newValue, .OBJC_ASSOCIATION_RETAIN_NONATOMIC)
        }
    }
    
    @objc func setOnClickWithHandler(_ handler: @escaping () -> Void) {
        self.onClickAction = handler
        let tap = UITapGestureRecognizer(target: self, action: #selector(handleTap(_:)))
        addGestureRecognizer(tap)
        isUserInteractionEnabled = true
    }
    
    @objc private func handleTap(_ sender: UITapGestureRecognizer) {
        onClickAction?()
    }
    
    @objc func setViewIdWithId(_ id: String) {
        self.accessibilityIdentifier = id
    }
    
    @objc func updateTextWithText(_ text: String) {
        // This will be handled by SwiftUI view updates
    }
}
