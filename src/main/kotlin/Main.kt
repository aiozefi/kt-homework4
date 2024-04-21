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
    val type: String?,
    val coordinates: String?,
    val place: Place?
)

data class Place(
    val title: String?,
    val country: String?,
    val city: String?
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
    val createdBy: Int?,
    val date: Int,
    val text: String,
    val replyOwnerId: Int?,
    val replyPostId: Int?,
    val friendsOnly: Boolean,
    val comments: Comments,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val postType: String,
    val attachments: Array <Attachment> = emptyArray(),
    val geo: Geo,
    val signerId: Int,
    val copyHistory: List<Any>?,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Int,
    val isFavorite: Boolean,
    val donut: Donut,
    val postponedId: Int?
)

abstract class Attachment(val type: String)

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int,
    val albumId: Int,
    val genreId: Int,
    val date: Int,
    val noSearch: Boolean,
    val isHq: Boolean
)
data class AudioAttachment(val audio: Audio): Attachment("audio")
data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val images: List<Image>,
    val firstFrames: List<Image>,
    val date: Int,
    val addingDate: Int,
    val views: Int,
    val localViews: Int?,
    val comments: Int?,
    val player: String,
    val platform: String?,
    val canAdd: Boolean,
    val isPrivate: Boolean?,
    val accessKey: String?,
    val processing: Boolean?,
    val isFavorite: Boolean,
    val canComment: Boolean,
    val canEdit: Boolean,
    val canLike: Boolean,
    val canRepost: Boolean,
    val canSubscribe: Boolean,
    val canAddToFaves: Boolean,
    val canAttachLink: Boolean,
    val width: Int,
    val height: Int,
    val userId: Int?,
    val converting: Boolean?,
    val added: Boolean?,
    val isSubscribed: Boolean?,
    val repeat: Boolean?,
    val type: String,
    val balance: Int?,
    val live: Boolean?,
    val liveStartTime: Int?,
    val liveStatus: String?,
    val upcoming: Boolean?,
    val spectators: Int?
)

data class Image(
    val height: Int,
    val url: String,
    val width: Int,
    val withPadding: Boolean?
)
data class VideoAttachment(val video: Video): Attachment("video")

data class StickerImage(
    val url: String,    // URL копии изображения
    val width: Int,
    val height: Int
)

data class Sticker(
    val productId: Int,                     // Идентификатор набора
    val stickerId: Int,
    val images: List<StickerImage>,
    val imagesWithBackground: List<StickerImage>,
    val animationUrl: String?,
    val isAllowed: Boolean
)
data class StickerAttachment(val sticker: Sticker): Attachment("sticker")

data class File(
    val id: Int,               // Идентификатор файла
    val ownerId: Int,          // Идентификатор пользователя, загрузившего файл
    val title: String,         // Название файла
    val size: Int,             // Размер файла в байтах
    val ext: String,           // Расширение файла
    val url: String,           // Адрес файла для загрузки
    val date: Int,             // Дата добавления в формате Unixtime
    val type: Int              // Тип файла
)
data class FileAttachment(val file: File): Attachment("file")
data class PhotoSize(
    val type: String,
    val url: String,
    val width: Int,
    val height: Int
)

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int?,
    val text: String?,
    val date: Int,
    val sizes: List<PhotoSize>,  // Массив копий изображения в разных размерах
    val width: Int,
    val height: Int
)

data class PhotoAttachment(val photo: Photo): Attachment("photo")
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

    fun clear() {
        posts = emptyArray()
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
        attachments = arrayOf(AudioAttachment(Audio(
            1,
            23,
            "Meto",
            "Шут",
            200,
            "url",
            1,
            2,
            3,
            1639923521,
            false,
            true))),
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
        attachments = emptyArray(),
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

    val attachment = post1.attachments[0]
    println(
        when (attachment){
            is AudioAttachment -> "Has audio : ${attachment.audio}"
            is VideoAttachment -> "Has video : ${attachment.video}"
            is PhotoAttachment -> "Has photo : ${attachment.photo}"
            is StickerAttachment -> "Has sticker : ${attachment.sticker}"
            is FileAttachment -> "Has file : ${attachment.file}"
            else -> "Unknown attachment"
        }
    )
}
