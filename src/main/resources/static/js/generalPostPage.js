document.addEventListener('DOMContentLoaded', () => {
    const deleteButton = document.getElementById('deletePostButton');
    const id = deleteButton.dataset.id;
    const generalBoardId = deleteButton.dataset.generalboardid;

    deleteButton.addEventListener('click', async () => {
        if (confirmDeletion()) {
            try {
                const response = await deletePost(generalBoardId, id);
                handleDeleteResponse(response, generalBoardId);
            } catch (error) {
                console.error('Error:', error);
            }
        }
    });
});

function confirmDeletion() {
    return confirm('해당 게시글을 삭제하시겠습니까?');
}

async function deletePost(generalBoardId, id) {
    return await fetch(`/api/generalBoard/${generalBoardId}/generalPost/${id}`, {
        method: 'DELETE'
    });
}

function handleDeleteResponse(response, generalBoardId) {
    if (response.ok) {
        window.location.href = `/generalBoard/${generalBoardId}`;
    } else {
        console.error('일반 게시글 삭제에 실패했습니다.');
    }
}

// 댓글 추가 버튼
const addCommentButton = document.getElementById('addCommentButton');
if (addCommentButton) {
    addCommentButton.addEventListener('click', async () => {
        const id = addCommentButton.dataset.id;
        const generalBoardId = addCommentButton.dataset.generalboardid;
        const commentContent = document.getElementById("commentContent").value;
        const userId = document.getElementById("userId").value;

        const data = {
            userId: userId,
            content: commentContent,
            postId: id
        }

        fetch(`/api/generalBoard/${generalBoardId}/generalPost/${id}/comment`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(res => {
            if (res.ok) {
                window.location.href = `/generalBoard/${generalBoardId}/generalPost/${id}`;
            }
        }).catch(error => {
            alert('Error:' + error);
        });
    });
}


