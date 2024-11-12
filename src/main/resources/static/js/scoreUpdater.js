setInterval(async () => {
    try {
        const response = await fetch("/api/user?sort=score");
        const data = await response.json();
        updateRankingTable(data);
    } catch (error) {
        console.error("Failed to fetch scores:", error);
    }
}, 5000); // 5초 간격으로 호출

function updateRankingTable(users) {
    const rankingList = document.getElementById('ranking-list');
    rankingList.innerHTML = ''; // 기존 내용을 비움

    users.forEach((user, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${index + 1}</td>
            <td class="nickname">${user.nickname}</td>
            <td>${user.rank}</td>
            <td class="score">${user.score}점</td>
        `;
        rankingList.appendChild(row);
    });
}