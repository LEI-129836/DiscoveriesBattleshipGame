package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata que representa a estrutura genérica de um navio na frota do jogo Batalha Naval.
 * Implementa a interface {@link IShip} e fornece a lógica base para a navegação,
 * verificação de posições, impacto de tiros e afundamento[cite: 1, 2].
 * 
 * @author Tomás Costa (LEI-129836)
 * @version 1.0
 */
public abstract class Ship implements IShip {

    /** Identificador da categoria Galeão. */
    private static final String GALEAO = "galeao";

    /** Identificador da categoria Fragata. */
    private static final String FRAGATA = "fragata";

    /** Identificador da categoria Nau. */
    private static final String NAU = "nau";

    /** Identificador da categoria Caravela. */
    private static final String CARAVELA = "caravela";

    /** Identificador da categoria Barca. */
    private static final String BARCA = "barca";

    /**
     * Método de fábrica (Factory Method) estático para instanciar objetos do tipo {@link Ship}
     * com base no nome do tipo de navio pretendido[cite: 1, 2].
     *
     * @param shipKind O nome do tipo de navio (ex.: "galeao", "fragata", "nau", "caravela", "barca")[cite: 1].
     * @param bearing A orientação/direção do navio na grelha[cite: 1].
     * @param pos A posição de referência/origem do navio na grelha[cite: 1].
     * @return Uma instância da subclasse concreta de {@link Ship} correspondente, ou {@code null} caso o tipo seja inválido.
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    /** Categoria/Tipo do navio[cite: 1]. */
    private String category;

    /** Orientação do navio na grelha[cite: 1]. */
    private Compass bearing;

    /** Posição de referência inicial do navio[cite: 1]. */
    private IPosition pos;

    /** Lista de posições ocupadas pelo navio na grelha[cite: 1]. */
    protected List<IPosition> positions;

    /**
     * Construtor da classe abstrata {@link Ship}.
     * Inicializa os dados do navio e valida se a orientação e posição de referência não são nulas.
     *
     * @param category A categoria/tipo do navio[cite: 1].
     * @param bearing A orientação do navio na grelha[cite: 1].
     * @param pos A posição inicial de referência na grelha[cite: 1].
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Obtém a categoria/nome do navio.
     *
     * @return A categoria do navio[cite: 1].
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Obtém a lista de posições ocupadas pelo navio no tabuleiro.
     *
     * @return Uma lista do tipo {@link List} contendo as posições {@link IPosition} do navio[cite: 1].
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Obtém a posição de referência inicial do navio.
     *
     * @return A posição {@link IPosition} de referência[cite: 1].
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Obtém a orientação do navio.
     *
     * @return O rumo/orientação {@link Compass} do navio[cite: 1].
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Verifica se o navio ainda se encontra a flutuar (ou seja, se pelo menos
     * uma das suas posições ainda não foi atingida por um disparo)[cite: 1].
     *
     * @return {@code true} se o navio ainda estiver a flutuar; {@code false} se todas as posições foram atingidas[cite: 1].
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Obtém o índice de linha da posição mais a norte (mais acima) ocupada pelo navio.
     *
     * @return O número da linha superior ocupada.
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Obtém o índice de linha da posição mais a sul (mais abaixo) ocupada pelo navio.
     *
     * @return O número da linha inferior ocupada.
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Obtém o índice de coluna da posição mais a oeste (mais à esquerda) ocupada pelo navio.
     *
     * @return O número da coluna mais à esquerda ocupada.
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Obtém o índice de coluna da posição mais a este (mais à direita) ocupada pelo navio.
     *
     * @return O número da coluna mais à direita ocupada.
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se este navio ocupa uma determinada posição da grelha.
     *
     * @param pos A posição {@link IPosition} a verificar[cite: 1].
     * @return {@code true} se o navio ocupar a posição; {@code false} caso contrário[cite: 1].
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se este navio está demasiado próximo de outro navio (encostado ou adjacente)[cite: 1].
     *
     * @param other O outro navio {@link IShip} com o qual se pretende comparar a proximidade[cite: 1].
     * @return {@code true} se estiver demasiado próximo; {@code false} caso contrário[cite: 1].
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se este navio está numa posição adjacente ou sobreposta a uma determinada coordenada[cite: 1].
     *
     * @param pos A posição {@link IPosition} a testar[cite: 1].
     * @return {@code true} se estiver adjacente/demasiado próxima; {@code false} caso contrário[cite: 1].
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * Regista um tiro numa dada posição da grelha. Se o navio ocupar essa posição,
     * a posição é assinalada como atingida[cite: 1].
     *
     * @param pos A posição {@link IPosition} visada pelo disparo[cite: 1].
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Devolve uma representação textual resumida do navio.
     *
     * @return Uma {@link String} contendo a categoria, orientação e posição de referência do navio.
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
