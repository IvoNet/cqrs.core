package nl.ivonet.cqrs.core.commands;

import nl.ivonet.cqrs.core.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseCommand extends Message {

    public BaseCommand(String id) {
        super(id);
    }
}
