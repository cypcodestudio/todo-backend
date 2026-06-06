-- TodoFlow schema

CREATE TYPE priority_enum AS ENUM ('LOW', 'MEDIUM', 'HIGH', 'URGENT');

CREATE TABLE todos (
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255)   NOT NULL,
    description  TEXT,
    completed    BOOLEAN        NOT NULL DEFAULT FALSE,
    priority     priority_enum  NOT NULL DEFAULT 'MEDIUM',
    due_date     DATE,
    created_at   TIMESTAMPTZ    NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ    NOT NULL DEFAULT NOW(),
    completed_at TIMESTAMPTZ
);

CREATE TABLE todo_tags (
    todo_id BIGINT       NOT NULL REFERENCES todos(id) ON DELETE CASCADE,
    tag     VARCHAR(100) NOT NULL,
    PRIMARY KEY (todo_id, tag)
);

-- Auto-update updated_at on row change
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER todos_updated_at
    BEFORE UPDATE ON todos
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE INDEX idx_todos_completed  ON todos(completed);
CREATE INDEX idx_todos_priority   ON todos(priority);
CREATE INDEX idx_todos_due_date   ON todos(due_date);
CREATE INDEX idx_todos_created_at ON todos(created_at DESC);
