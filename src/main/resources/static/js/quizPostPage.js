document.addEventListener('DOMContentLoaded', () => {
    const deleteButton = document.getElementById('deletePostButton');
    const id = deleteButton.dataset.id;
    const quizBoardId = deleteButton.dataset.quizboardid;

    deleteButton.addEventListener('click', async () => {
        if (confirmDeletion()) {
            try {
                const response = await deletePost(quizBoardId, id);
                handleDeleteResponse(response, quizBoardId);
            } catch (error) {
                console.error('Error:', error);
            }
        }
    });
});

function confirmDeletion() {
    return confirm('해당 게시글을 삭제하시겠습니까?');
}

async function deletePost(quizBoardId, id) {
    return await fetch(`/api/quizBoard/${quizBoardId}/quizPost/${id}`, {
        method: 'DELETE'
    });
}

function handleDeleteResponse(response, quizBoardId) {
    if (response.ok) {
        window.location.href = `/quizBoard/${quizBoardId}`;
    } else {
        console.error('퀴즈 게시글 삭제에 실패했습니다.');
    }
}

// 댓글 추가 버튼
const addCommentButton = document.getElementById('addCommentButton');
if (addCommentButton) {
    addCommentButton.addEventListener('click', async () => {
        const id = addCommentButton.dataset.id;
        const quizBoardId = addCommentButton.dataset.quizboardid;
        const commentContent = document.getElementById("commentContent").value;
        const userId = document.getElementById("userId").value;

        const data = {
            userId: userId,
            content: commentContent,
            postId: id
        }

        fetch(`/api/quizBoard/${quizBoardId}/quizPost/${id}/comment`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(res => {
            if (res.ok) {
                window.location.href = `/quizBoard/${quizBoardId}/quizPost/${id}`;
            }
        }).catch(error => {
            alert('Error:' + error);
        });
    });
}


