// 게시판 삭제

function deleteBoard(deleteButton) {
    // 삭제 요청 URL 설정
    const boardId = deleteButton.getAttribute("data-boardId");
    console.log(boardId);
    const deleteUrl = `/api/admin/deleteBoard/generalBoard/${boardId}`;

    fetch(`/api/admin/deleteBoard/generalBoard/${boardId}`, {
        method: 'GET'
    }).then(() => {
        alert('삭제가 완료되었습니다');
        location.reload();
    });
}

// 게시판 버튼 클릭 시 /admin/quizBoard/{boardId} id 변경
function redirectToBoard(button) {
    const boardId = button.getAttribute("data-boardId");

    // 필요 시 리다이렉트
    if (boardId) {
        window.location.href = `/admin/generalBoard/${boardId}`;
    } else {
        console.error("Board ID is missing");
    }
}