// DOM 요소들을 조건부로 가져오기
const password = document.getElementById('password');
const verifyPwd = document.getElementById('verify_pwd');
const email = document.getElementById('email');
const nickname = document.getElementById('nickname');
const message = document.getElementById('message');
const joinButton = document.getElementById('join_button');
const passwordChangeForm = document.getElementById('password_change_form');
const ValidationMessage = document.getElementById('validation_message');

let PasswordMatch = false;
let PasswordRule = false;

// 비밀번호 일치 검증 함수
function validatePasswordMatch() {
    if (password.value === verifyPwd.value) {
        message.style.color = 'green';
        message.textContent = '비밀번호가 일치합니다.';
        PasswordMatch = true;
    } else {
        message.style.color = 'red';
        message.textContent = '비밀번호가 일치하지 않습니다.';
        PasswordMatch = false;
    }
}

// 비밀번호 유효성 검사 함수
function validatePasswordRule(pwd) {
    const rules = {
        length: pwd.length >= 6 && pwd.length <= 20,
        lowercase: /[a-z]/.test(pwd),
        uppercase: /[A-Z]/.test(pwd),
        specialChar: /[!@#$%^&*(),.?":{}|<>]/.test(pwd)
    };

    let validationMessages = [];
    if (!rules.length) validationMessages.push("- 비밀번호는 6자 이상 20자 이하여야 합니다.");
    if (!rules.lowercase) validationMessages.push("- 영문 소문자를 포함해야 합니다.");
    if (!rules.uppercase) validationMessages.push("- 영문 대문자를 포함해야 합니다.");
    if (!rules.specialChar) validationMessages.push("- 특수문자를 포함해야 합니다.");

    PasswordRule = Object.values(rules).every(rule => rule === true);

    if (validationMessages.length > 0) {
        ValidationMessage.style.color = 'red';
        ValidationMessage.innerHTML = "비밀번호 규칙:<br>" + validationMessages.join('<br>');
    } else {
        ValidationMessage.style.color = 'green';
        ValidationMessage.textContent = "비밀번호가 규칙에 맞습니다.";
    }

    return PasswordRule;
}

// 비밀번호 & 비밀번호 확인 입력 시 이벤트
if (password && verifyPwd) {
    password.addEventListener('input', function () {
        validatePasswordRule(password.value);
        validatePasswordMatch();
    });

    verifyPwd.addEventListener('input', validatePasswordMatch);
}

// 회원가입 button 이벤트
if (joinButton) {
    joinButton.addEventListener('click', function (event) {
        if (!PasswordRule) {
            alert('비밀번호가 규칙에 맞지 않습니다.');
            event.preventDefault();
            return;
        }

        if (!PasswordMatch) {
            alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
            event.preventDefault();
            return;
        }

        const param = {
            'email': email.value,
            'password': password.value,
            'nickname': nickname.value
        }

        fetch(`/api/signup`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(param)
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(text);
                    });
                }
                alert('회원가입이 완료되었습니다!');
                window.location.href = '/login';
                // return response.json;
            })
            .catch(error => {
                alert(error.message);
                console.error('Error:', error);
            });
    });
}

// 비밀번호 변경 submit 이벤트
if (passwordChangeForm) {
    passwordChangeForm.addEventListener('submit', function (event) {
        if (!PasswordRule) {
            alert('비밀번호가 규칙에 맞지 않습니다.');
            event.preventDefault();
            return;
        }

        if (!PasswordMatch) {
            alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
            event.preventDefault();
            return;
        }

        alert('비밀번호가 변경 되었습니다!');
    });
}