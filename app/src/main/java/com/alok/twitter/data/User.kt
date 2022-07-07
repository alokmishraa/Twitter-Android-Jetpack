package com.alok.twitter.data

data class User(
    val userName: String = "alok_",
    val displayName: String = "alok",
    val profileUrl: String = "https://graph.facebook.com/945387882269493/picture?type=small",
    val following: Number = 200,
    val follower: Number = 50
)
