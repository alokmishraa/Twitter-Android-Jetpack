package com.alok.twitter.data

data class Tweet(
    val userName: String = "alok_",
    val displayName: String = "alok",
    val profileUrl: String = "https://graph.facebook.com/945387882269493/picture?type=large",
    val numberOfComments: Number = 100,
    val numberOfRetweet: Number = 20,
    val numberOfLikes: Number = 15,
    val content: String = "Nature has given us all the pieces required to achieve exceptional wellness and health, but has left it to us to put these pieces together.",
    val media: String = "",
)
