data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean
)

data class Comments(
    val count: Int,
    val canPost: Boolean,
    val groupsCanPost: Boolean,
    val canClose: Boolean,
    val canOpen: Boolean
)

data class Reposts(
    val count: Int,
    val userReposted: Boolean
)

data class Views(
    val count: Int
)

data class Geo(
    val type: String,
    val coordinates: String,
    val place: Place
)

data class Place(
    val title: String,
    val country: String,
    val city: String
)

data class Donut(
    val isDonut: Boolean,
    val paidDuration: Int,
    val placeholder: Placeholder,
    val canPublishFreeCopy: Boolean,
    val editMode: String
)

data class Placeholder(
    val description: String,
    val title: String
)

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val comments: Comments,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val postType: String,
    val attachments: List<Any>,
    val geo: Geo,
    val signerId: Int,
    val copyHistory: List<Any>,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Int,
    val isFavorite: Boolean,
    val donut: Donut,
    val postponedId: Int
)

object WallService {
    private var nextId: Int = 1
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        val newPost = post.copy(id = nextId++)
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        val existingPostIndex = posts.indexOfFirst { it.id == post.id }

        return if (existingPostIndex != -1) {
            posts[existingPostIndex] = post
            true
        } else {
            false
        }
    }

    fun getPosts(): List<Post> {
        return posts.toList()
    }
}

fun main() {
    val post1 = Post(
        id = 1,
        ownerId = 100,
        fromId = 101,
        createdBy = 101,
        date = 1639923542,
        text = "Первый пост",
        replyOwnerId = 0,
        replyPostId = 0,
        friendsOnly = false,
        comments = Comments(5, true, true, false, true),
        likes = Likes(10, true, true, true),
        reposts = Reposts(2, true),
        views = Views(100),
        postType = "post",
        attachments = emptyList(),
        geo = Geo("point", "56.8372,60.5960", Place("Екатеринбург", "Россия", "Екатеринбург")),
        signerId = 0,
        copyHistory = emptyList(),
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        markedAsAds = 0,
        isFavorite = false,
        donut = Donut(false, 0, Placeholder("Donut description", "Donut title"), false, "all"),
        postponedId = 0
    )

    val post2 = Post(
        id = 2,
        ownerId = 200,
        fromId = 201,
        createdBy = 201,
        date = 1639923600,
        text = "Второй пост",
        replyOwnerId = 0,
        replyPostId = 0,
        friendsOnly = true,
        comments = Comments(3, true, false, true, true),
        likes = Likes(8, false, true, true),
        reposts = Reposts(1, false),
        views = Views(50),
        postType = "post",
        attachments = emptyList(),
        geo = Geo("point", "55.751244,37.618423", Place("Москва", "Россия", "Москва")),
        signerId = 0,
        copyHistory = emptyList(),
        canPin = false,
        canDelete = false,
        canEdit = false,
        isPinned = false,
        markedAsAds = 0,
        isFavorite = true,
        donut = Donut(false, 0, Placeholder("Donut description", "Donut title"), false, "all"),
        postponedId = 0
    )

    WallService.add(post1)
    WallService.add(post2)

    println("Оригинальные посты:")
    WallService.getPosts().forEach { println(it) }

    // Обновление второго поста
    val updatedPost2 = post2.copy(text = "Другой текст.")
    WallService.update(updatedPost2)

    println("\nОтредактированные посты:")
    WallService.getPosts().forEach { println(it) }
}
