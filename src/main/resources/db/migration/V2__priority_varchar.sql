-- Hibernate EnumType.STRING sends VARCHAR; cast the column away from the custom enum type.
-- The priority_enum type is left in place (harmless) since DROP TYPE CASCADE is too destructive.
ALTER TABLE todos
    ALTER COLUMN priority TYPE VARCHAR(10)
    USING priority::TEXT;
