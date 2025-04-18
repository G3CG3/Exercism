import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class YachtTest {

    @Test
    public void yacht() {
        Yacht yacht = new Yacht(new int[]{ 5, 5, 5, 5, 5 }, YachtCategory.YACHT);
        assertThat(yacht.score()).isEqualTo(50);
    }

    @Test
    public void notYacht() {
        Yacht yacht = new Yacht(new int[]{ 1, 3, 3, 2, 5 }, YachtCategory.YACHT);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void ones() {
        Yacht yacht =  new Yacht(new int[]{ 1, 1, 1, 3, 5 }, YachtCategory.ONES);
        assertThat(yacht.score()).isEqualTo(3);
    }

    @Test
    public void onesOutOfOrder() {
        Yacht yacht = new Yacht(new int[]{ 3, 1, 1, 5, 1 }, YachtCategory.ONES);
        assertThat(yacht.score()).isEqualTo(3);
    }

    @Test
    public void noOnes() {
        Yacht yacht = new Yacht(new int[]{ 4, 3, 6, 5, 5 }, YachtCategory.ONES);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void twos() {
        Yacht yacht = new Yacht(new int[]{ 2, 3, 4, 5, 6 }, YachtCategory.TWOS);
        assertThat(yacht.score()).isEqualTo(2);
    }

    @Test
    public void fours() {
        Yacht yacht = new Yacht(new int[]{ 1, 4, 1, 4, 1 }, YachtCategory.FOURS);
        assertThat(yacht.score()).isEqualTo(8);
    }

    @Test
    public void yachtCountedAsThrees() {
        Yacht yacht = new Yacht(new int[]{ 3, 3, 3, 3, 3 }, YachtCategory.THREES);
        assertThat(yacht.score()).isEqualTo(15);
    }

    @Test
    public void yachtOfThreesCountedAsFives() {
        Yacht yacht = new Yacht(new int[]{ 3, 3, 3, 3, 3 }, YachtCategory.FIVES);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void fives() {
        Yacht yacht = new Yacht(new int[]{ 1, 5, 3, 5, 3 }, YachtCategory.FIVES);
        assertThat(yacht.score()).isEqualTo(10);
    }

    @Test
    public void sixes() {
        Yacht yacht = new Yacht(new int[]{ 2, 3, 4, 5, 6 }, YachtCategory.SIXES);
        assertThat(yacht.score()).isEqualTo(6);
    }

    @Test
    public void fullHouseTwoSmallThreeBig() {
        Yacht yacht = new Yacht(new int[]{ 2, 2, 4, 4, 4 }, YachtCategory.FULL_HOUSE);
        assertThat(yacht.score()).isEqualTo(16);
    }

    @Test
    public void fullHouseThreeSmallTwoBig() {
        Yacht yacht = new Yacht(new int[]{ 5, 3, 3, 5, 3 }, YachtCategory.FULL_HOUSE);
        assertThat(yacht.score()).isEqualTo(19);
    }

    @Test
    public void twoPairIsNotAFullHouse() {
        Yacht yacht = new Yacht(new int[]{ 2, 2, 4, 4, 5 }, YachtCategory.FULL_HOUSE);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void fourOfAKindIsNotAFullHouse() {
        Yacht yacht = new Yacht(new int[]{ 1, 4, 4, 4, 4 }, YachtCategory.FULL_HOUSE);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void yachtIsNotAFullHouse() {
        Yacht yacht = new Yacht(new int[]{ 2, 2, 2, 2, 2 }, YachtCategory.FULL_HOUSE);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void fourOfAKind() {
        Yacht yacht = new Yacht(new int[]{ 6, 6, 4, 6, 6 }, YachtCategory.FOUR_OF_A_KIND);
        assertThat(yacht.score()).isEqualTo(24);
    }

    @Test
    public void yachtCanBeScoredAsFourOfAKind() {
        Yacht yacht = new Yacht(new int[]{ 3, 3, 3, 3, 3 }, YachtCategory.FOUR_OF_A_KIND);
        assertThat(yacht.score()).isEqualTo(12);
    }

    @Test
    public void fullHouseIsNotFourOfAKind() {
        Yacht yacht = new Yacht(new int[]{ 3, 3, 3, 5, 5 }, YachtCategory.FOUR_OF_A_KIND);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void littleStraight() {
        Yacht yacht = new Yacht(new int[]{ 3, 5, 4, 1, 2 }, YachtCategory.LITTLE_STRAIGHT);
        assertThat(yacht.score()).isEqualTo(30);
    }

    @Test
    public void littleStraightAsBigStraight() {
        Yacht yacht = new Yacht(new int[]{ 1, 2, 3, 4, 5 }, YachtCategory.BIG_STRAIGHT);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void fourInOrderButNotALittleStraight() {
        Yacht yacht = new Yacht(new int[]{ 1, 1, 2, 3, 4 }, YachtCategory.LITTLE_STRAIGHT);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void noPairsButNotALittleStraight() {
        Yacht yacht = new Yacht(new int[]{ 1, 2, 3, 4, 6 }, YachtCategory.LITTLE_STRAIGHT);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void minimumIs1MaximumIs5ButNotALittleStraight() {
        Yacht yacht = new Yacht(new int[]{ 1, 1, 3, 4, 5 }, YachtCategory.LITTLE_STRAIGHT);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void bigStraight() {
        Yacht yacht = new Yacht(new int[]{ 4, 6, 2, 5, 3 }, YachtCategory.BIG_STRAIGHT);
        assertThat(yacht.score()).isEqualTo(30);
    }

    @Test
    public void bigStraightAsLittleStraight() {
        Yacht yacht = new Yacht(new int[]{ 6, 5, 4, 3, 2 }, YachtCategory.LITTLE_STRAIGHT);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void noPairsButNotABigStraight() {
        Yacht yacht = new Yacht(new int[]{ 6, 5, 4, 3, 1 }, YachtCategory.BIG_STRAIGHT);
        assertThat(yacht.score()).isEqualTo(0);
    }

    @Test
    public void choice() {
        Yacht yacht = new Yacht(new int[]{ 3, 3, 5, 6, 6 }, YachtCategory.CHOICE);
        assertThat(yacht.score()).isEqualTo(23);
    }

    @Test
    public void yachtAsChoice() {
        Yacht yacht = new Yacht(new int[]{ 2, 2, 2, 2, 2 }, YachtCategory.CHOICE);
        assertThat(yacht.score()).isEqualTo(10);
    }

}
