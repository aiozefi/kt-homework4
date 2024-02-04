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
            text = "Test post",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comments = Comments(0, false, false, false, false),
            likes = Likes(0, false, false, false),
            reposts = Reposts(0, false),
            views = Views(0),
            postType = "post",
            attachments = emptyList(),
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
            text = "Original post",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comments = Comments(0, false, false, false, false),
            likes = Likes(0, false, false, false),
            reposts = Reposts(0, false),
            views = Views(0),
            postType = "post",
            attachments = emptyList(),
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
            attachments = emptyList(),
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

