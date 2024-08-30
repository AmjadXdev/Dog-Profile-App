package com.amjadxdev.dogprofilepage

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Composable
fun DogProfileApp(modifier: Modifier = Modifier) {
    DogProfile()
}

@Composable
fun DogProfile(modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            .border(2.dp, Color.White, RoundedCornerShape(30.dp))
    )
    {

//        content of your card - Including Dog image, description, follower, following and posts

//        androidx.compose.foundation.layout.BoxWithConstraints() {
//            val configuration = LocalConfiguration.current
            val configuration = LocalConfiguration.current
            val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
            val constraints
            =
                if (isPortrait) {
                portraitConstraints(margin = 16.dp)
//                portraitConstraints(margin = 16.dp)
            }
            else
            {
                landScapeConstraints(margin = 16.dp)

//                TODO call landscape constraints
            }

        ConstraintLayout(constraintSet = constraints) {
//            val (image,
//                nameText,
//                countryText,
//                followers,
//                followButton,
//                buttonMessage) = createRefs()
//
//            val topGuideline =
//                createGuidelineFromTop(0.2f)
            Image(
                painter = painterResource(id = R.drawable.husky),
                contentDescription = "husky",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Red, CircleShape)
                    .layoutId("image")
//                    .constrainAs(image) {
//                        top.linkTo(topGuideline)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }
                ,
                contentScale = ContentScale.Crop
            )

            Text(text = "Siberian Husky",
                modifier = Modifier
                    .layoutId("nameText")
//                    .constrainAs(nameText) {
//                        top.linkTo(image.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }
            )
            Text(text = "Germany",
                modifier = Modifier.layoutId("countryText")
//                    .constrainAs(countryText) {
//                        top.linkTo(nameText.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .layoutId("followers")
//                    .constrainAs(followers) {
//                        top.linkTo(countryText.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }
            ) {
                profileStates("150", "Followers")
                profileStates("100", "Following")
                profileStates("30", "Posts")
            }


            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .layoutId("followButton")
//                    .constrainAs(followButton) {
//                        top.linkTo(followers.bottom, margin = 16.dp)
//                        start.linkTo(parent.start)
//                        end.linkTo(buttonMessage.start)
//                    }
            ) {

                Text(text = "Follow User")
            }

            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .layoutId("buttonMessage")
//                    .constrainAs(buttonMessage) {
//                        top.linkTo(followers.bottom, margin = 16.dp)
//                        start.linkTo(followButton.end)
//                        end.linkTo(parent.end)
//                    }
            ) {
                Text(text = "Direct Message")
            }
        }
//        }
    }

}

@Composable
fun profileStates(count: String, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}


fun portraitConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val followers = createRefFor("followers")
        val followButton = createRefFor("followButton")
        val buttonMessage = createRefFor("buttonMessage")
        val topGuideline = createGuidelineFromTop(0.3f)


        constrain(image) {
            top.linkTo(topGuideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(nameText) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(countryText) {
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(followers) {
            top.linkTo(countryText.bottom)
        }

        constrain(followButton) {
            top.linkTo(followers.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(buttonMessage.start)
            width = Dimension.wrapContent
        }

        constrain(buttonMessage) {
            top.linkTo(followers.bottom, margin = margin)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }


    }
}


private fun landScapeConstraints(margin : Dp) : ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val followers = createRefFor("followers")
        val followButton = createRefFor("followButton")
        val buttonMessage = createRefFor("buttonMessage")
        val topGuideline = createGuidelineFromTop(0.1f)


        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }

        constrain(nameText) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(image.end)
        }

        constrain(countryText) {
            top.linkTo(nameText.bottom)
            start.linkTo(image.start)
            end.linkTo(image.end)
        }

        constrain(followers) {
            top.linkTo(parent.top)
            start.linkTo(image.end)
            end.linkTo(parent.end)
        }

        constrain(followButton) {
            top.linkTo(followers.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(buttonMessage.start)
            width = Dimension.wrapContent
        }

        constrain(buttonMessage) {
            top.linkTo(followers.bottom, margin = margin)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }


    }

}

@Preview(showSystemUi = true
//    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
private fun DogProfilePreview() {
    DogProfileApp()
}