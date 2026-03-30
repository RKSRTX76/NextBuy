package com.rksrtx76.nextbuy.presentation.userprofile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.rksrtx76.nextbuy.R
import com.rksrtx76.nextbuy.domain.model.UserProfile
import com.rksrtx76.nextbuy.presentation.navigation.Routes
import com.rksrtx76.nextbuy.presentation.userprofile.components.UserProfileTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    navController: NavHostController,
    viewModel: UserProfileViewModel = hiltViewModel()
){
    val uiState by viewModel.state.collectAsState()
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var stateName by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }

    LaunchedEffect(uiState.userProfile) {
        firstName = uiState.userProfile.firstName
        lastName = uiState.userProfile.lastName
        email = uiState.userProfile.email
        phoneNumber = uiState.userProfile.phoneNumber
        address = uiState.userProfile.address
        city = uiState.userProfile.city
        stateName = uiState.userProfile.state
        pinCode = uiState.userProfile.pinCode
        country = uiState.userProfile.country
    }



    // State for Profile image selection
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Gallery launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ){ uri : Uri? ->
        uri?.let {
            selectedImageUri = it
        }
    }

    // Auto-populate Userprofile details
//    LaunchedEffect() { }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "User Profile",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .background(Color.White)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                // Profile image with edit icon
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable{
                            galleryLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    contentAlignment = Alignment.Center
                ){
                    // Display priority: Google profile photo > Selected image > Default avatar
                    val photoModel = when{
                        // here order matters
                        selectedImageUri != null ->{
                            selectedImageUri
                        }
                        uiState.userProfile.profilePhotoUrl.isNotBlank() ->{
                            uiState.userProfile.profilePhotoUrl
                        }
                        else ->{
                            R.drawable.woman
                        }
                    }

                    AsyncImage(
                        model = photoModel,
                        contentDescription = "Profile Photo",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color(0xFFE0E0E0), CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    // edit icon badge
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(24.dp)
                            .background(Color.Gray.copy(alpha = 0.5f), CircleShape)
                            .clickable{
                                galleryLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Update Profile Image",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                SectionHeader(title = "Personal Details")

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    UserProfileTextField(
                        label = "First Name",
                        value = firstName,
                        onValueChange = {
                            firstName = it
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    UserProfileTextField(
                        label = "Last Name",
                        value = lastName,
                        onValueChange = {
                            lastName = it
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                UserProfileTextField(
                    label = "Email Address",
                    value = email,
                    onValueChange = {
                        // read only
                    },
                    enabled = false,
                )

                Spacer(modifier = Modifier.height(12.dp))

                UserProfileTextField(
                    label = "Phone No",
                    value = phoneNumber,
                    onValueChange = {
                        phoneNumber = it
                    },
                    enabled = true,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column {
                    UserProfileTextField(
                        label = "Password",
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        isPassword = true
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                if(password.isNotBlank()){
                                    viewModel.onEvent(UserProfileEvent.PasswordChanged(password))
                                }
                                password = ""
                            },
                            contentPadding = PaddingValues(0.dp)   // removed TextButton default padding
                        ) {
                            Text(
                                text = "Change Password",
                                fontSize = 12.sp,
                                color = Color(0xFFF83758),
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp
                )
                Spacer(modifier = Modifier.height(12.dp))

                SectionHeader("Business Address Details")

                Spacer(modifier = Modifier.height(12.dp))

                UserProfileTextField(
                    label = "Address",
                    value = address,
                    onValueChange = {
                        address = it
                    },
                )

                Spacer(modifier = Modifier.height(12.dp))

                UserProfileTextField(
                    label = "Pin Code",
                    value = pinCode,
                    onValueChange = {
                        pinCode = it
                    },
                )

                Spacer(modifier = Modifier.height(12.dp))

                UserProfileTextField(
                    label = "City",
                    value = city,
                    onValueChange = {
                        city = it
                    },
                )

                Spacer(modifier = Modifier.height(12.dp))

                UserProfileTextField(
                    label = "State",
                    value = stateName,
                    onValueChange = {
                        stateName = it
                    },
                )

                Spacer(modifier = Modifier.height(12.dp))

                UserProfileTextField(
                    label = "Country",
                    value = country,
                    onValueChange = {
                        country = it
                    },
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        // Update user profile
                        viewModel.onEvent(UserProfileEvent.FirstNameChanged(firstName))
                        viewModel.onEvent(UserProfileEvent.LastNameChanged(lastName))
                        viewModel.onEvent(UserProfileEvent.PhoneNumberChanged(phoneNumber))
                        viewModel.onEvent(UserProfileEvent.AddressChanged(address))
                        viewModel.onEvent(UserProfileEvent.PinCodeChanged(pinCode))
                        viewModel.onEvent(UserProfileEvent.CityChanged(city))
                        viewModel.onEvent(UserProfileEvent.StateChanged(stateName))
                        viewModel.onEvent(UserProfileEvent.CountryChanged(country))
                        if(selectedImageUri != null){
                            viewModel.onEvent(UserProfileEvent.ProfilePhotoChanged(selectedImageUri.toString()))
                        }

                        viewModel.onEvent(UserProfileEvent.UpdateProfile)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF83758)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !uiState.isSaving
                ){
                    if (uiState.isSaving) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = "Save",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

            }
        }
    }
}



@Composable
fun SectionHeader(
    title : String
){
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black
    )
}