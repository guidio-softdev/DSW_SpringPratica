document.addEventListener('DOMContentLoaded', () => {
    const API_URL = 'http://localhost:8080/api/tarefas';
    

    const taskListView = document.getElementById('task-list-view');
    const taskFormView = document.getElementById('task-form-view');
    const taskListContainer = document.getElementById('task-list');
    const taskForm = document.getElementById('task-form');
    const messageArea = document.getElementById('message-area');
    const formTitle = document.getElementById('form-title');
    const taskIdInput = document.getElementById('task-id');
    

    const btnShowForm = document.getElementById('btn-show-form');
    const btnCancel = document.getElementById('btn-cancel');
    

    const modalConfirmacao = document.getElementById('modal-confirmacao');
    const btnConfirmarExclusao = document.getElementById('btn-confirmar-exclusao');
    const btnCancelarExclusao = document.getElementById('btn-cancelar-exclusao');
    const msgConfirmacao = document.getElementById('msg-confirmacao');
    
    let idParaExcluir = null;


    const switchView = (view) => {
        if (view === 'form') {
            taskListView.classList.add('hidden');
            taskFormView.classList.remove('hidden');
        } else {
            taskFormView.classList.add('hidden');
            taskListView.classList.remove('hidden');
            messageArea.classList.add('hidden');
        }
    };

    const showMessage = (message, type) => {
        messageArea.textContent = message;
        messageArea.className = `message-area ${type}`;
        messageArea.classList.remove('hidden');
 
        window.scrollTo(0, 0);
        setTimeout(() => messageArea.classList.add('hidden'), 5000);
    };


    const loadTasks = async () => {
        try {
            const response = await fetch(API_URL);
            if (!response.ok) throw new Error('Erro ao carregar tarefas.');
            const tasks = await response.json();

            taskListContainer.innerHTML = '';

            tasks.forEach(task => {
                const taskCard = document.createElement('div');
                taskCard.className = 'task-card';

       
                const hoje = new Date();
                hoje.setHours(0,0,0,0);
        
                const dataPartes = task.dataTermino.split('-');
                const dataTermino = new Date(dataPartes[0], dataPartes[1]-1, dataPartes[2]);
                
                let statusHtml = '';
                if (dataTermino < hoje) {
                    statusHtml = '<span class="status atrasada">ATRASADA</span>';
                }

                const dataFormatada = dataTermino.toLocaleDateString('pt-BR');

                taskCard.innerHTML = `
                    <h3>${task.titulo} ${statusHtml}</h3>
                    <p><strong>Responsável:</strong> ${task.responsavel}</p>
                    <p><strong>Data de Término:</strong> ${dataFormatada}</p>
                    ${task.detalhamento ? `<p class="detalhamento">${task.detalhamento}</p>` : ''}
                    <div class="form-buttons" style="margin-top:10px; justify-content: flex-start;">
                        <button class="btn-editar" data-id="${task.id}" style="width: auto; margin: 0; background-color: #007bff;">Alterar</button>
                        <button class="btn-excluir" data-id="${task.id}" style="width: auto; margin: 0; background-color: #dc3545;">Excluir</button>
                    </div>
                `;
                taskListContainer.appendChild(taskCard);
            });

            
            document.querySelectorAll('.btn-editar').forEach(btn => {
                btn.addEventListener('click', () => carregarParaEdicao(btn.dataset.id));
            });
            document.querySelectorAll('.btn-excluir').forEach(btn => {
                btn.addEventListener('click', () => abrirModalExclusao(btn.dataset.id));
            });

        } catch (error) {
            showMessage(error.message, 'error');
        }
    };


    taskForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const id = taskIdInput.value;
        const taskData = {
            titulo: document.getElementById('titulo').value,
            responsavel: document.getElementById('responsavel').value,
            dataTermino: document.getElementById('dataTermino').value,
            detalhamento: document.getElementById('detalhamento').value
        };

        const method = id ? 'PUT' : 'POST';
        const url = id ? `${API_URL}/${id}` : API_URL;

        try {
            const response = await fetch(url, {
                method: method,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(taskData)
            });

            if (!response.ok) throw new Error('Erro ao salvar tarefa, Verifique os Dados novamente.');

            showMessage('Tarefa salva com sucesso!', 'success');
            limparFormulario();
            switchView('list');
            loadTasks();
        } catch (error) {
            showMessage(error.message, 'error');
        }
    });

  
    const carregarParaEdicao = async (id) => {
        try {
            const response = await fetch(`${API_URL}/${id}`);
            if (!response.ok) throw new Error('Erro ao buscar tarefa.');
            const task = await response.json();

       
            taskIdInput.value = task.id;
            document.getElementById('titulo').value = task.titulo;
            document.getElementById('responsavel').value = task.responsavel;
            document.getElementById('dataTermino').value = task.dataTermino;
            document.getElementById('detalhamento').value = task.detalhamento || '';

            formTitle.textContent = `Alterar tarefa - ID ${task.id}`;
            switchView('form');
        } catch (error) {
            showMessage(error.message, 'error');
        }
    };


    const abrirModalExclusao = (id) => {
        idParaExcluir = id;
        msgConfirmacao.textContent = `Deseja excluir a tarefa ID ${id}?`;
        modalConfirmacao.classList.remove('hidden');
    };

    btnConfirmarExclusao.addEventListener('click', async () => {
        if (idParaExcluir) {
            try {
                const response = await fetch(`${API_URL}/${idParaExcluir}`, { method: 'DELETE' });
                if (!response.ok) throw new Error('Erro ao excluir.');
                
                modalConfirmacao.classList.add('hidden');
                showMessage('Tarefa excluída com sucesso!', 'success');
                loadTasks();
            } catch (error) {
                modalConfirmacao.classList.add('hidden');
                showMessage(error.message, 'error');
            }
        }
    });

    btnCancelarExclusao.addEventListener('click', () => {
        modalConfirmacao.classList.add('hidden');
        idParaExcluir = null;
    });


    const limparFormulario = () => {
        taskForm.reset();
        taskIdInput.value = '';
        formTitle.textContent = 'Nova tarefa';
    };

    btnShowForm.addEventListener('click', () => {
        limparFormulario();
        switchView('form');
    });

    btnCancel.addEventListener('click', () => {
        limparFormulario();
        switchView('list');
    });


    loadTasks();
});