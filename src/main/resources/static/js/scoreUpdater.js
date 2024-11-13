async function fetchAndUpdateRanking() {
    try {
        const response = await fetch("/api/user?sort=score,desc&size=10");
        const data = await response.json();
        console.log(data);
        updateRankingTable(data);
    } catch (error) {
        console.error("Failed to fetch scores:", error);
    }
}

function updateRankingTable(users) {
    const rankingList = document.getElementById('ranking-list');
    rankingList.innerHTML = ''; // 기존 내용을 비움

    users.forEach((user, index) => {
        const row = document.createElement('tr');
        const score = user.score ?? 0;
        let rankIcon;
        if (index === 0) {
            rankIcon = '🥇'; // 1등 메달 아이콘
        } else if (index === 1) {
            rankIcon = '🥈'; // 2등 메달 아이콘
        } else if (index === 2) {
            rankIcon = '🥉'; // 3등 메달 아이콘
        } else {
            rankIcon = `${index + 1}`; // 4등부터는 숫자로 표시
        }

        row.innerHTML = `
        <td>${rankIcon}</td>
        <td class="nickname">${user.nickname}</td>
        <td>${user.authority}</td>
        <td class="score">${score}점</td>
    `;
        rankingList.appendChild(row);
    });
}

// 페이지 로드 시 데이터를 즉시 가져와 업데이트
fetchAndUpdateRanking();

// 5초마다 업데이트
setInterval(fetchAndUpdateRanking, 5000);
