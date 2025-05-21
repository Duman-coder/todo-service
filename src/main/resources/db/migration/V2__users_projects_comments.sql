-- Таблица задач (tasks)
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50),
    priority VARCHAR(50),
    deadline TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    assigned_user_id UUID,
    project_id UUID
);

-- Таблица проектов (projects)
CREATE TABLE projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50),
    owner_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Таблица комментариев (comments)
CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    task_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_task FOREIGN KEY (task_id) REFERENCES tasks(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
