package com.example.lab8

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.layout.ContentScale

@Composable
fun SignUp(navController: NavController) {
    val context = LocalContext.current
    val firebaseAuth = remember { FirebaseAuth.getInstance() }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val AccentRed = Color(0xFFC70039)
    val BgColor = Color(0xFFFCF7F0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor)
    ) {
        // Phần hình ảnh Pizza (Top Banner)
        Image(
            painter = painterResource(id = R.drawable.pizza1),
            contentDescription = "Pizza Image",
            contentScale = ContentScale.Crop, // Quan trọng để ảnh lấp đầy không gian
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Chiều cao ảnh
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 80.dp, // Bán kính lớn tạo hình vòm
                        bottomEnd = 80.dp  // Bán kính lớn tạo hình vòm
                    )
                )
        )

        // Phần Content Form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "CREATE YOUR PIZZERIA ACCOUNT",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AccentRed
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Text Field cho Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AccentRed,
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Text Field cho Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = {
                    // Bọc Icon trong Box và áp dụng Modifier.size() để kiểm soát kích thước
                    // Điều chỉnh 24.dp theo kích thước mong muốn của bạn
                    Box(modifier = Modifier.size(24.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_lock),
                            contentDescription = "Lock Icon",
                            tint = AccentRed,
                            modifier = Modifier.fillMaxSize() // Đảm bảo icon lấp đầy Box
                        )
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AccentRed,
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Nút Sign Up
            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
                                // Đăng ký thành công, sử dụng .rout
                                navController.navigate(Screen.Home.rout) {
                                    popUpTo(Screen.SignUp.rout) { inclusive = true }
                                }
                            } else {
                                Toast.makeText(context, task.exception?.message ?: "Registration Failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = AccentRed),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Sign Up", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Link Sign In
            Row {
                Text("Already have an account?", color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Sign In now!",
                    color = AccentRed,
                    fontWeight = FontWeight.Bold,
                    // Điều hướng đến Signin, sử dụng .rout
                    modifier = Modifier.clickable { navController.navigate(Screen.SignIn.rout) }
                )
            }
        } // End Column Form
    }
}