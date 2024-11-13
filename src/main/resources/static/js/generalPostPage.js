document.addEventListener('DOMContentLoaded', () => {
    const deleteButton = document.getElementById('deletePostButton');
    const id = deleteButton.dataset.id;
    const generalBoardId = deleteButton.dataset.generalBoardId;

    deleteButton.addEventListener('click', async () => {
        if (confirmDeletion()) {
            try {
                const response = await deletePost(generalBoardId, id);
                handleResponse(response, generalBoardId);
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
    return await fetch(`/generalBoard/${generalBoardId}/generalPost/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

function handleResponse(response, generalBoardId) {
    if (response.ok) {
        window.location.href = `/generalBoard/${generalBoardId}`;
    } else {
        console.error('일반 게시글 삭제에 실패했습니다.');
    }
}