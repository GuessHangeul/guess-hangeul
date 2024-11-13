document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('quizPostForm');

    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        const formData = new FormData(form);
        const data = getFormData(formData);

        try {
            const response = await submitForm(form.action, data);
            handleResponse(response);
        } catch (error) {
            console.error('Error:', error);
        }
    });
});

function getFormData(formData) {
    return {
        answer: formData.get('title'),
        hintContent: formData.get('content')
    };
}

async function submitForm(actionUrl, data) {
    return await fetch(actionUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

function handleResponse(response) {
    if (response.ok) {
        window.location.href = `/quizBoard/${quizBoardId}`;
    } else {
        console.error('Failed to create the quiz post.');
    }
}