package com.example.lab8

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
// Đảm bảo import Screen class của bạn (ví dụ: com.example.lab8.Screen)

@Composable
fun HomeScreen(navController: NavController) {
    val firebaseAuth = remember { FirebaseAuth.getInstance() }

    val PrimaryYellow = Color(0xFFFFC700)
    val AccentRed = Color(0xFFC70039)
    val LightDot = Color(0xFFE0E0E0)
    val DarkDot = Color(0xFF808080)

    // Sử dụng Box để xếp chồng: Background, Column (Nội dung), Image (Rider)
    Box(modifier = Modifier.fillMaxSize().background(PrimaryYellow)) {

        // Cột chính chứa toàn bộ nội dung (Text, Button, Spacer)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight() // Chiếm toàn bộ chiều cao
                .padding(top = 48.dp)
                // THAY ĐỔI QUAN TRỌNG: Thêm Padding Bottom để tạo vùng an toàn trên ảnh pizman
                // Giá trị 260.dp = 300.dp (chiều cao ảnh) - 40.dp (khoảng cách an toàn)
                .padding(bottom = 260.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            // Căn giữa nội dung theo chiều dọc trong khu vực padding (từ 48.dp đến 260.dp)
            verticalArrangement = Arrangement.Center
        ) {

            // Tiêu đề và Tagline (Phần trên)
            Text(
                text = "PIZZERIA",
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = AccentRed,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            Text(
                text = "Delivering Deliciousness right to your door!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 48.dp)
            )

            // Dot Indicator
            Row(
                Modifier.padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.size(8.dp).background(DarkDot, shape = MaterialTheme.shapes.small))
                Box(modifier = Modifier.size(8.dp).background(LightDot, shape = MaterialTheme.shapes.small))
                Box(modifier = Modifier.size(8.dp).background(LightDot, shape = MaterialTheme.shapes.small))
            }

            Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách giữa Dots và Buttons

            // Nút START ORDER
            Button(
                onClick = { /* Logic cho nút START ORDER */ },
                colors = ButtonDefaults.buttonColors(containerColor = AccentRed),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text("START ORDER", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nút SignOut
            Button(
                onClick = {
                    firebaseAuth.signOut()
                    // ĐÃ SỬA: Dùng Screen.Signin.rout
                    navController.navigate(Screen.SignIn.rout) {
                        popUpTo(Screen.Home.rout) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = AccentRed),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text("SignOut", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }

            // XÓA Spacer(weight)
        } // End Column

        // Phần hình ảnh nền dưới cùng (Rider and Pizza)
        Image(
            painter = painterResource(id = R.drawable.pizman),
            contentDescription = "Rider and Pizza",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Dùng height cố định
                .align(Alignment.BottomCenter) // Gắn vào phía dưới cùng của Box
        )
    } // End Box
}