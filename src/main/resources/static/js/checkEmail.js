const check_email = document.getElementById('check_email');
let Email = '';

// 이메일 중복 확인 버튼 이벤트
check_email.addEventListener('click', function () {
    Email = document.getElementById('email').value;

    if (!Email) {
        showModal('이메일을 입력해주세요.', false);
        return;
    }

    const param = {
        'email': Email
    };

    fetch('/api/checkEmailDuplicate', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(param)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('서버 응답 오류');
            } else {
                return response.json();
            }
        })
        .then(data => {
            if (data.isExists) {
                showModal('입력하신 이메일이 존재합니다.', true);
            } else {
                showModal('입력하신 이메일이 존재하지 않습니다.', false);
            }
        })
        .catch(error => {
            showModal('서버 오류가 발생했습니다. 다시 시도해주세요.', false);
            console.error('Error:', error);
        });
});


// 이메일 확인 모달 함수
function showModal(message, showResetButton = false) {
    const modalMessage = document.getElementById('modalMessage');
    const modalFooter = document.getElementById('modalFooter');
    const modal = new bootstrap.Modal(document.getElementById('resultModal'));

    modalMessage.textContent = message;

    // 비밀번호 변경 버튼 제거 및 추가 처리
    const ResetButton = document.getElementById('resetPasswordButton');
    if (ResetButton) {
        ResetButton.remove();
    }

    if (showResetButton) {
        const resetButton = document.createElement('button');
        resetButton.id = 'resetPasswordButton';
        resetButton.className = 'btn btn-primary';
        resetButton.textContent = '비밀번호 재설정';
        resetButton.onclick = sendPasswordResetEmail;
        modalFooter.insertBefore(resetButton, modalFooter.firstChild);
    }

    modal.show();
}

// 비밀번호 변경 버튼 이벤트
function sendPasswordResetEmail() {
    fetch(`api/resetPasswordRequest/${Email}`, {
        method: 'POST'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('서버 응답 오류');
            }
            return response.json();
        })
        .then(data => {
            // resultModal 모달 닫기
            const resultModal = bootstrap.Modal.getInstance(document.getElementById('resultModal'));
            resultModal.hide();

            // passwordResetModal 모달 표시
            document.getElementById('passwordResetMessage').textContent =
                `${Email}로 비밀번호 재설정 링크가 발송되었습니다. 이메일을 확인해주세요.`;
            const resetModal = new bootstrap.Modal(document.getElementById('passwordResetModal'));
            resetModal.show();
        })
        .catch(error => {
            showModal('비밀번호 재설정 메일 발송 중 오류가 발생했습니다.', false);
            console.error('Error:', error);
        });
}