function createBoard() {
    const boardTitle = document.getElementById("board-title").value;
    const data = {
        title: boardTitle
    };

    fetch('/api/quizBoard', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                alert("게시판을 생성했습니다.");
                window.location.replace("/quizBoard");
                return response.json();
            } else if (response.status === 409) {
                throw new Error("이미 존재하는 게시판 제목입니다.");
            } else if (response.status === 403) {
                throw new Error("권한이 없습니다.");
            } else {
                throw new Error('게시판 생성 실패');
            }
        })
        .catch(error => {
            alert(error);
        });
}