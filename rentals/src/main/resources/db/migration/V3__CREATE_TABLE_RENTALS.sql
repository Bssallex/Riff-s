CREATE TABLE TB_RENTALS (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tag VARCHAR(50) NOT NULL,
    date_rentals DATE NOT NULL,
    date_devolution DATE NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_rentals_user FOREIGN KEY (user_id) REFERENCES tb_user (id)
);

-- Tabela intermediária da relação ManyToMany
CREATE TABLE TB_RENTALS_INSTRUMENT (
    rentals_id UUID NOT NULL,
    instrument_id BIGINT NOT NULL,
    PRIMARY KEY (rentals_id, instrument_id),
    CONSTRAINT fk_rentals_instrument_rentals FOREIGN KEY (rentals_id) REFERENCES tb_rentals (id) ON DELETE CASCADE,
    CONSTRAINT fk_rentals_instrument_instrument FOREIGN KEY (instrument_id) REFERENCES tb_instrument (id) ON DELETE CASCADE
);



