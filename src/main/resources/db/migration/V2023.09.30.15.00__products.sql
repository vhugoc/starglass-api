CREATE TABLE material (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    merchant_id TEXT NOT NULL references merchant(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    type TEXT NOT NULL,
    name TEXT NOT NULL,
    color TEXT DEFAULT NULL,
    thickness TEXT DEFAULT NULL,
    value FLOAT NOT NULL
);

CREATE TABLE product (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    merchant_id TEXT NOT NULL references merchant(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    name TEXT DEFAULT NULL,
    description TEXT NOT NULL
);

CREATE TABLE product_materials (
    product_fk TEXT,
    material_fk TEXT,
    FOREIGN KEY (product_fk) REFERENCES product(id),
    FOREIGN KEY (material_fk) REFERENCES material(id),
    PRIMARY KEY (product_fk, material_fk)
);
