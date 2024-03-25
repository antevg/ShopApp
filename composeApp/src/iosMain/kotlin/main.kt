import androidx.compose.ui.window.ComposeUIViewController
import com.eacipher.shopapp.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
