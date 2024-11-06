package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import android.graphics.Paint
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    val profilePicture = painterResource(R.drawable.profile_picture)
    val fullName = stringResource(R.string.full_name)
    val title = stringResource(R.string.title)
    val phoneNumber = stringResource(R.string.phone_number)
    val socialMedia = stringResource(R.string.social_media)
    val email = stringResource(R.string.email)
    val iconColor = 0xFFb69bc7
    val titleColor = 0xFFb69bc7

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        FullNameSection(profilePicture, fullName, title, titleColor)
        ContactInfoSection(phoneNumber, socialMedia, email, iconColor)
    }
}

@Composable
fun FullNameSection(profilePicture: Painter, fullName: String,
                    title: String, titleColor: Long, modifier: Modifier = Modifier) {
    var fullNameFontSize = 45
    var titleFontSize = (fullNameFontSize / 2).toInt()
    // TODO: dynamic adjust any string so that it should fix to the screen

    Column (
        horizontalAlignment =  Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFD2E8D4))
    ) {
        Image(
            painter = profilePicture,
            contentDescription = null,
            alpha = 0.9F,
            modifier = modifier
                .padding(top = 170.dp)
                .size(200.dp)
        )
        Text(
            text = fullName,
            fontWeight = FontWeight.Bold,
            fontSize = fullNameFontSize.sp,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(top = 10.dp, bottom = 10.dp)
        )
        Text(
            text = title,
            fontSize = titleFontSize.sp,
            fontWeight = FontWeight.Bold,
            color = Color(titleColor),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun ContactInfoSection(phoneNumber: String, socialMedia: String,
                       email: String, iconColor: Long, modifier: Modifier = Modifier) {

    // Logic to find `startPadding` to make sure the contain in the middle of screen regardless of inputs
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    var maxLength = maxOf(phoneNumber.length, socialMedia.length, email.length)
    var maxStr = when (maxLength) {
        phoneNumber.length -> phoneNumber
        socialMedia.length -> socialMedia
        email.length -> email
        else -> ""
    }
    // font 16 --> 22 pixel
    // font 14 --> 19 pixel
    val iconPixel = 30
    val paint = Paint()
    paint.textSize = 16f
    val widthPixel = paint.measureText(maxStr + iconPixel).toInt()

    // Check if the screen size has enough pixel
    var startPadding = 0;
    if (widthPixel < screenWidthDp) {
        startPadding = (screenWidthDp - widthPixel) / 2
    }

    Column (
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
    ) {
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(start = startPadding.dp, bottom = 20.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.phone_icon),
                tint = Color(iconColor),
                contentDescription = null
            )
            Text(
                text = phoneNumber,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(start = 5.dp)
            )
        }
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(start = startPadding.dp, bottom = 20.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.link_icon),
                tint = Color(iconColor),
                contentDescription = null
            )
            Text(
                text = socialMedia,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(start = 5.dp)
            )
        }
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(start = startPadding.dp, bottom = 20.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.email_icon),
                tint = Color(iconColor),
                contentDescription = null
            )
            Text(
                text = email,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(start = 5.dp)
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true)
@Composable
fun GreetingPreview() {
    var profilePicture = painterResource(R.drawable.profile_picture)
    FullNameSection(profilePicture, "Leon Nguyen", "Software Dev Engineering Intern", 0xFFb69bc7)
    ContactInfoSection("+1 (657) 246 9341", "www.linkedin.com/in/leon-thien-nguyen", "shinichi5442@gmail.com", 0xFFb69bc7)
}