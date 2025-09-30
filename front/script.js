document.addEventListener('DOMContentLoaded', () => {
    const taskListView = document.getElementById('task-list-view');
    const taskFormView = document.getElementById('task-form-view');
    const taskListContainer = document.getElementById('task-list');
    const taskForm = document.getElementById('task-form');
    const messageArea = document.getElementById('message-area');

    const btnShowForm = document.getElementById('btn-show-form');
    const btnCancel = document.getElementById('btn-cancel');
    const btnAddResponsavel = document.getElementById('btn-add-responsavel');

    const API_URL = 'http://localhost:8080/tarefas';

    const switchView = (viewToShow) => {
        if (viewToShow === 'form') {
            taskListView.classList.add('hidden');
            taskFormView.classList.remove('hidden');
        } else {
            taskFormView.classList.add('hidden');
            taskListView.classList.remove('hidden');
            messageArea.classList.add('hidden');
            messageArea.textContent = '';
        }
    };

    const showMessage = (message, type) => {
        messageArea.textContent = message;
        messageArea.className = `message-area ${type}`;
        messageArea.classList.remove('hidden');

        setTimeout(() => {
            messageArea.classList.add('hidden');
        }, 4000);
    };

    const loadTasks = async () => {
        try {
            const response = await fetch(API_URL);
            if (!response.ok) {
                throw new Error('Falha ao carregar as tarefas.');
            }
            const tasks = await response.json();

            taskListContainer.innerHTML = '';

            tasks.forEach(task => {
                const taskCard = document.createElement('div');
                taskCard.className = 'task-card';

                const hoje = new Date();
                hoje.setHours(0, 0, 0, 0);
                const dataTermino = new Date(task.dataTermino + 'T00:00:00');

                let statusHtml = '';
                if (dataTermino < hoje) {
                    statusHtml = '<span class="status atrasada">ATRASADA</span>';
                }

                const dataFormatada = dataTermino.toLocaleDateString('pt-BR');

                taskCard.innerHTML = `
                    <h3>${task.titulo} ${statusHtml}</h3>
                    <p><strong>Responsável(is):</strong> ${task.responsavel}</p>
                    <p><strong>Data de Término:</strong> ${dataFormatada}</p>
                    ${task.detalhamento ? `<p class="detalhamento">${task.detalhamento}</p>` : ''}
                `;
                taskListContainer.appendChild(taskCard);
            });

        } catch (error) {
            showMessage(error.message, 'error');
        }
    };

    const addResponsavelInput = () => {
        const container = document.getElementById('responsaveis-container');
        const newInputGroup = document.createElement('div');
        newInputGroup.className = 'responsavel-input-group';

        newInputGroup.innerHTML = `
            <input type="text" name="responsavel" class="responsavel-input" placeholder="Nome do responsável" required>
            <button type="button" class="btn-remover">Remover</button>
        `;

        container.appendChild(newInputGroup);

        newInputGroup.querySelector('.btn-remover').addEventListener('click', () => {
            newInputGroup.remove();
        });
    };

    btnShowForm.addEventListener('click', () => switchView('form'));
    btnCancel.addEventListener('click', () => {
        taskForm.reset();
        switchView('list');
    });

    btnAddResponsavel.addEventListener('click', addResponsavelInput);

    taskForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const responsaveisInputs = document.querySelectorAll('.responsavel-input');
        const responsaveisNomes = Array.from(responsaveisInputs)
                                       .map(input => input.value.trim())
                                       .filter(nome => nome)
                                       .join(', ');

        const formData = new FormData(taskForm);
        const taskData = {
            titulo: formData.get('titulo'),
            responsavel: responsaveisNomes,
            dataTermino: formData.get('dataTermino'),
            detalhamento: formData.get('detalhamento'),
        };

        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(taskData)
            });

            if (!response.ok) {
                throw new Error('Falha ao salvar a tarefa. Verifique os dados e tente novamente.');
            }

            showMessage('Tarefa salva com sucesso!', 'success');
            taskForm.reset();
            document.querySelectorAll('#responsaveis-container .responsavel-input-group:not(:first-child)').forEach(el => el.remove());

            await loadTasks();
            switchView('list');

        } catch (error) {
            showMessage(error.message, 'error');
        }
    });

    loadTasks();
});