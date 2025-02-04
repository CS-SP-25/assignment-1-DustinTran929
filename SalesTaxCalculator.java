interface SalesTaxBehavior {
    // Method to compute sales tax
    double compute(double value);
}

// NoTax class for states with no sales tax
class NoTax implements SalesTaxBehavior {
    @Override
    public double compute(double value) {
        return 0.0; // No sales tax
    }
}

// SevenPercent class for states with 7% sales tax
class SevenPercent implements SalesTaxBehavior {
    @Override
    public double compute(double value) {
        return value * 0.07; // 7% sales tax
    }
}

// FourPercent class for stats with 4.5% sales tax
class FourPercent implements SalesTaxBehavior {
    @Override
    public double compute(double value) {
        return value * 0.045; // 4.5% sales tax
    }
}
// Abstract State class
abstract class State {
    protected String name;
    protected SalesTaxBehavior salesTaxBehavior;

    public State(String name, SalesTaxBehavior salesTaxBehavior) {
        this.name = name;
        this.salesTaxBehavior = salesTaxBehavior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showTax(double value) {
        double tax = salesTaxBehavior.compute(value);
        System.out.printf("The sales tax on $%.2f in %s is $%.2f.%n", value, name, tax);
    }
}

// Alaska class with no sales tax
class Alaska extends State {
    public Alaska() {
        super("Alaska", new NoTax());
    }
}

// Indiana class with 7% sales tax
class Indiana extends State {
    public Indiana() {
        super("Indiana", new SevenPercent());
    }
}

// Hawaii class with 4.5% sales tax
class Hawaii extends State {
    public Hawaii() {
        super("Hawaii", new FourPercent());
    }
}

// Main class
public class SalesTaxCalculator {
    public static void main(String[] args) {
        // Validate command-line arguments
        if (args.length != 2) {
            System.out.println("Error: Please provide the state name and sale amount as command-line arguments.");
            return;
        }

        String stateName = args[0];
        String saleAmountString = args[1];

        try {
            // Parse the sale amount
            double saleAmount = Double.parseDouble(saleAmountString);

            // Create state object based on the input
            State state;
            if (stateName.equalsIgnoreCase("Indiana")) {
                state = new Indiana();
            } else if (stateName.equalsIgnoreCase("Alaska")) {
                state = new Alaska();
            } else if (stateName.equalsIgnoreCase("Hawaii")) {
                state = new Hawaii();
            } else {
                System.out.println("Error: Invalid state name. Only 'Indiana' or 'Alaska' are allowed.");
                return;
            }

            // Display the sales tax
            state.showTax(saleAmount);

        } catch (NumberFormatException e) {
            System.out.println("Error: Sale amount must be a valid number.");
        }
    }
}


