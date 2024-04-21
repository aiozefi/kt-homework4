import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class WallServiceTest {

    @Test
    fun addPost_shouldAssignNonZeroId() {
        val post = Post(
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

        val addedPost = WallService.add(post)

        assertNotEquals(0, addedPost.id)
    }

    @Test
    fun updatePost_shouldReturnTrueForExistingId() {
        val post = Post(
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

        WallService.add(post)

        val updatedPost = post.copy(text = "Updated post")
        val result = WallService.update(updatedPost)

        assertEquals(true, result)
    }

    @Test
    fun updatePost_shouldReturnFalseForNonExistingId() {
        val post = Post(
            id = -1,
            ownerId = 100,
            fromId = 101,
            createdBy = 101,
            date = 1639923542,
            text = "Original post",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comments = Comments(0, false, false, false, false),
            likes = Likes(0, false, false, false),
            reposts = Reposts(0, false),
            views = Views(0),
            postType = "post",
            attachments = emptyArray(),
            geo = Geo("point", "0,0", Place("", "", "")),
            signerId = 0,
            copyHistory = emptyList(),
            canPin = false,
            canDelete = false,
            canEdit = false,
            isPinned = false,
            markedAsAds = 0,
            isFavorite = false,
            donut = Donut(false, 0, Placeholder("", ""), false, "all"),
            postponedId = 0
        )

        val result = WallService.update(post)

        assertEquals(false, result)
    }
}

