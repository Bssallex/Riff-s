package dev.bssallex.rentals.configuration;

import dev.bssallex.rentals.entity.Instrument;
import dev.bssallex.rentals.entity.Rentals;
import dev.bssallex.rentals.entity.User;

import java.util.List;

public class TextEmail {

    private static String gerarListaInstrumentos(List<Instrument> instrumentos) {
        StringBuilder sb = new StringBuilder();
        instrumentos.forEach(i -> sb.append("""
• %s %s
  - Tipo: %s
  - Preço: %s

""".formatted(
                i.getBrand(),
                i.getModel(),
                i.getTypeInstrument(),
                i.getPrice()
        )));
        return sb.toString();
    }

    public static String gerarEmail(User user, Rentals rentals, String tag, List<Instrument> instrumentos) {

        return """
Olá, %s!

Seu aluguel foi realizado com sucesso. Aqui estão os detalhes do seu comprovante:

--------------------------------------------------
🔖 TAG DO ALUGUEL: %s
📅 Data de Retirada: %s
📅 Data de Devolução: %s
--------------------------------------------------

🎸 INSTRUMENTOS ALUGADOS:
%s
--------------------------------------------------
Obrigado por utilizar o nosso sistema de aluguel de instrumentos!
Riff's Music 🎵
""".formatted(
                user.getName(),
                tag,
                rentals.getDateRentals(),
                rentals.getDateDevolution(),
                gerarListaInstrumentos(instrumentos)
        );
    }


}
