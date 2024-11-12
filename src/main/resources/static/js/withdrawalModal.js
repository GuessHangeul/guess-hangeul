function checkWithdrawalConfirmInput() {
    const withdrawalInput = document.getElementById("withdrawal-input");
    const value = withdrawalInput.value;
    return value === "탈퇴를 원합니다";
}


var withdrawalConfirmBtn = document.getElementById("confirm-withdrawal-btn");
if (withdrawalConfirmBtn) {
    withdrawalConfirmBtn.addEventListener('click', function () {
        if (!checkWithdrawalConfirmInput()) {
            var feedbackMessage = document.getElementById("feedback-message");
            feedbackMessage.style.visibility = "visible";
            return;
        }
        ;

        fetch('/api/selfWithdrawal', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('회원탈퇴 요청 실패');
                }
            })
            .then(data => {
                if (data === true) {
                    alert("회원 탈퇴가 완료되었습니다.");
                    window.location.href = "/api/logout";
                }
            })
            .catch(error => {
                // console.error('에러:', error);
                alert(error);
            });
    });
}

var cancelWithdrawalBtn = document.getElementById("cancel-withdrawal-btn");
if (cancelWithdrawalBtn) {
    cancelWithdrawalBtn.addEventListener('click', function () {
        document.getElementById("withdrawal-input").value = "";
        document.getElementById("feedback-message").style.visibility = "hidden";
    });
}
