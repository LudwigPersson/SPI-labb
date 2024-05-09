import org.example.provider.Dkk;
import org.example.provider.Euro;
import org.example.service.CurrencyConverter;

module org.example.provider {

    requires org.example.service;

    exports org.example.provider;

    provides CurrencyConverter with Euro, Dkk;
}