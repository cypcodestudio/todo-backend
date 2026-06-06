ALTER TABLE todos
    ADD CONSTRAINT chk_priority
    CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH', 'URGENT'));
