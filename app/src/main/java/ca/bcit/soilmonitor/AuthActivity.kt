package ca.bcit.soilmonitor

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ca.bcit.soilmonitor.ui.theme.SoilMonitorTheme
import com.amplifyframework.ui.authenticator.SignedInState
import com.amplifyframework.ui.authenticator.ui.Authenticator
import com.amplifyframework.ui.authenticator.ui.SignUp

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Authenticator(
                signUpContent = { signUpState ->
                    SignUp(
                        state = signUpState,
                        headerContent = {
                            Column {
                                Text(
                                    style = MaterialTheme.typography.titleLarge,
                                    text = stringResource(R.string.amplify_ui_authenticator_title_signup)
                                )
                                Divider()
                            }
                        }
                    )
                }
            ) { state ->
                SignedInContent(state)
            }
        }
    }

    @Composable
    private fun SignedInContent(state: SignedInState) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}