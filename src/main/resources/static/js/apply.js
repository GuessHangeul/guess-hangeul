function applyBoardManager() {
    console.log("createBoard 함수 호출됨");
    fetch('/api/boardManagerApply', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                alert("신청 완료됐습니다.");
                window.location.replace("/newBoardManagerApply");
                return response.json();
            } else {
                return response.text().then(text => { // JSON이 아닌 응답을 확인할 경우
                    throw new Error('신청 실패: ' + text);
                });
            }
        })
        .catch(error => {
            console.error("에러 발생:", error);
            alert("신청 중 오류가 발생했습니다. 다시 시도해주세요.");
        });
}