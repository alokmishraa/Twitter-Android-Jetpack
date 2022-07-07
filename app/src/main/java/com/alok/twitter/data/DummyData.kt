@file:Suppress("SpellCheckingInspection")

package com.alok.twitter.data

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils.HSLToColor
import com.alok.twitter.model.ImageHolder
import com.alok.twitter.model.Post
import com.alok.twitter.model.User
import java.util.*
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextFloat
import kotlin.random.Random.Default.nextInt

object DummyData {

	// ----------------------------------------------
	// Private
	// ----------------------------------------------
	private fun getRandomName(): String {
		return listOf(
			"${getRandomString(1)}.O.${getRandomString(1)}.Y",
			"Jim",
			"幽波紋 DAM",
			"El${getRandomString(4)}",
			"${getRandomString(4)} COMMISSION OPEN",
			"${getRandomString(4)} El Magnifico",
			"13/02/1999 Never Forget",
			"Джон Сина",
			"ボブ・レノン",
			"طماطم حمراء"
		).random()
	}

	private fun getRandomHandle(): String {
		return listOf(
			"Boby${nextInt(0, 10000).toString().padStart(4)}",
			"Jimmy${nextInt(0, 1000000).toString().padStart(6)}",
			"Jojo____Fan__${nextInt(0, 100).toString().padStart(2)}",
			"Filo_${nextInt(0, 1000).toString().padStart(3)}",
			"Mula${nextInt(0, 1000000).toString().padStart(6)}",
			"Nyto__${nextInt(0, 1000).toString().padStart(3)}",
			"ESO-${getRandomString(3).padStart(3)}",
		).random()
	}

	private fun getRandomString(length: Int, uppercase: Boolean = true, lowercase: Boolean = true, numbers: Boolean = false): String {
		if (!lowercase && !numbers && !uppercase)
			return ""

		val allowedChars: MutableList<Char> = mutableListOf()
		if (uppercase)
			allowedChars += ('A'..'Z')
		if (lowercase)
			allowedChars += ('a'..'z')
		if (numbers)
			allowedChars += ('0'..'9')

		return (1..length)
			.map { allowedChars.random() }
			.joinToString("")
	}

	private fun getRandomLoremIpsum(): String {
		return listOf(
			"Lorem#ipsum dolor sit amet, #consectetur adipiscing \uD83D\uDE00 elit. Praesent #pulvinar erat eget auctor ultricies. Vestibulum id purus iaculis, semper mauris id, mollis velit. Maecenas in tempor metus. Sed sed lectus tellus. Duis condimentum odio arcu, nec sodales nisl feugiat at. Nulla ut nisi eu lorem pulvinar efficitur. Nunc risus felis, fringilla et tempor is, convallis quis dolor. Mauris varius mattis imperdiet. Quisque ullamcorper erat ut dui tempus gravida. Maecenas laoreet et quam vel fringilla. Quisque sed libero varius, auctor augue non, viverra mi. Praesent cursus enim eu mauris suscipit ornare. In pulvinar nulla finibus ante ultrices, at rhoncus nulla tristique. Ut at sapien ac massa iaculis pharetra non quis metus. Morbi quis ullamcorper diam, sit amet blandit velit.",
			"Mauris#euismod rutrum mauris, vitae tincidunt ❤️ turpis fringilla vel. #Praesent vehicula urna sed ligula dignissim, vitae rhoncus augue imperdiet. Etiam ac sem justo. Quisque condimentum tincidunt urna, non bibendum nisl. Donec sollicitudin tortor eget nisl ultrices, non mattis tellus varius. Curabitur ultricies lorem et nibh venenatis laoreet. Etiam orci tellus, tincidunt et iaculis sit amet, scelerisque ut velit. Donec et malesuada lectus. Maecenas elit lectus, consequat ultricies dictum aliquet, rutrum a tortor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin ornare ullamcorper volutpat. Vestibulum ligula augue, commodo ut condimentum nec, bibendum varius mauris.",
			"Pellentesque semper at #eros ❤️ convallis lobortis. Etiam quam mi, #eleifend eget finibus eu, auctor vel metus. Aliquam leo enim, vulputate in justo at, condimentum viverra velit. Aenean ex turpis, laoreet ac mi ut, consectetur facilisis ligula. Morbi a maximus lorem. Curabitur vitae enim ullamcorper, fringilla tellus eu, tristique libero. Sed lectus mauris, pulvinar vitae interdum quis, bibendum ut urna. Donec dolor turpis, consequat eget tortor elementum, elementum blandit risus. Morbi blandit dolor vel lectus eleifend, ut auctor massa vehicula. Morbi aliquam arcu augue, sed mollis enim feugiat id. Aliquam vitae felis at elit laoreet cursus sit amet id nunc. Nam eget ex id magna elementum ultricies. Praesent tempus purus a lectus aliquam ultrices. Maecenas id felis fermentum, vestibulum sem eget, vulputate ipsum.",
			"Cras\uD83D\uDE1B\uD83D\uDE1B sit #amet elementum neque. Nunc condimentum ex elit ✨✨, ac #posuere nulla pellentesque non. Suspendisse id aliquet eros. Praesent efficitur vulputate ullamcorper. Proin ultrices nibh felis, ut iaculis elit dignissim quis. Phasellus lobortis enim ornare, vulputate elit vel, pretium lorem. Donec leo erat, bibendum non tellus nec, ornare ultricies massa. Aenean elementum neque sit amet ornare bibendum. Donec ultrices nibh risus, dictum scelerisque lectus vestibulum et. Aliquam sem risus, semper eget facilisis nec, sollicitudin quis nisi. Etiam vel tincidunt ipsum. Curabitur eu dui eleifend, luctus ipsum lobortis, euismod leo. Vivamus porta luctus tortor in efficitur. Phasellus pulvinar tincidunt quam, vitae imperdiet magna aliquam at. Nulla ut mattis odio.",
			"Donec id #magna massa \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89. Nullam malesuada, massa eget lobortis viverra, quam mi ullamcorper nisi, consectetur commodo dolor ante ut odio. Donec pulvinar eget metus vel sodales. Nunc non sem sit amet sapien ornare facilisis. Nam tellus diam, ornare nec turpis dictum, convallis malesuada elit. In sagittis tellus convallis, euismod enim at, efficitur enim. Curabitur ornare condimentum ligula, a tempus tellus consectetur at. Nunc nec felis at tellus imperdiet consequat id ut nisl. Proin blandit odio vel laoreet ultricies. Proin non pretium elit."
		).random().substring(0, nextInt(20, 250))
	}

	private fun getRandomColor(): Color {
		val hue = nextFloat() * 360
		val sat = nextFloat() * (.6f - .2f) + .2f
		val value = nextFloat() * (.6f - .35f) + .6f
		return Color(HSLToColor(floatArrayOf(hue, sat, value)))
	}

	private fun getRandomAvatar(notRandom: Int? = null): ImageHolder {
		return ImageHolder(400, 400, getRandomColor(), "https://i.pravatar.cc/400?img=${notRandom ?: nextInt(0, 50)}")
	}

	private fun getRandomImage(): ImageHolder {
		val imageWidth = nextInt(600, 1921)
		val imageHeight = nextInt(400, 1081)
		return ImageHolder(
			width = imageWidth,
			height = imageHeight,
			colorAverage = getRandomColor(),
			online = "https://picsum.photos/seed/${nextInt(0, 200)}/$imageWidth/$imageHeight"
		)
	}

	private fun getRandomPost(): Post {
		val imageList: List<ImageHolder> = List(nextInt(0, 10)) {
			getRandomImage()
		}

		return Post(
			userUID = getRandomUser().uid,
			content = getRandomLoremIpsum(),
			timestamp = Date(),
			likeCount = nextInt(0, 10),
			reblogCount = nextInt(0, 10),
			commentsCount = nextInt(0, 10),
			liked = nextBoolean(),
			reblogged = nextBoolean(),
			imageList = imageList
		)
	}

	val UserList = List(50) {
		User(
			uid = UUID.randomUUID(),
			name = getRandomName(),
			handle = getRandomHandle(),
			verified = nextBoolean(),
			follower = nextInt(0, 50000),
			following = nextInt(0, 500000),
			avatar = getRandomAvatar(it)
		)
	}

	private fun getRandomUser(): User = UserList.random()

	// ----------------------------------------------
	// Public
	// ----------------------------------------------

	val currentUser: User = UserList.random()

	val postsList = List(500) {
		getRandomPost()
	}

	fun List<User>.getUserFromUID(uid: UUID): User {
		return find { it.uid == uid } ?: User(
			UUID(0, 0), "ERROR", "ERROR", verified = false,
			follower = 0,
			following = 0,
		)
	}
}