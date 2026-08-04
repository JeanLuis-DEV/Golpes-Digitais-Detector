package Modelos;

public class Moedas {
    public boolean contemMoedas(String mensagemNormalizada) {
        return contemMoedasBrasileiras(mensagemNormalizada)
                || contemMoedasInternacionais(mensagemNormalizada)
                || contemCryptomoedas(mensagemNormalizada);
    }

    private boolean contemMoedasBrasileiras(String mensagemNormalizada) {
        return mensagemNormalizada.contains("real")
                || mensagemNormalizada.contains("reais");
    }

    private boolean contemMoedasInternacionais(String mensagemNormalizada) {
        return mensagemNormalizada.contains("dolar")
                || mensagemNormalizada.contains("dolares")
                || mensagemNormalizada.contains("usd")
                || mensagemNormalizada.contains("euro")
                || mensagemNormalizada.contains("euros")
                || mensagemNormalizada.contains("eur")
                || mensagemNormalizada.contains("libra")
                || mensagemNormalizada.contains("libras")
                || mensagemNormalizada.contains("gbp")
                || mensagemNormalizada.contains("peso")
                || mensagemNormalizada.contains("pesos")
                || mensagemNormalizada.contains("yen")
                || mensagemNormalizada.contains("franco")
                || mensagemNormalizada.contains("francos");
    }

    private boolean contemCryptomoedas(String mensagemNormalizada) {
        return mensagemNormalizada.contains("bitcoin")
                || mensagemNormalizada.contains("btc")
                || mensagemNormalizada.contains("ethereum")
                || mensagemNormalizada.contains("eth")
                || mensagemNormalizada.contains("cripto")
                || mensagemNormalizada.contains("criptomoeda");
    }

}
