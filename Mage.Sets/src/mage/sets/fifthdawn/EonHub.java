/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mage.sets.fifthdawn;

import java.util.UUID;
import mage.constants.*;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.cards.CardImpl;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.players.Player;

/**
 *
 * @author nick.myers
 */
public class EonHub extends CardImpl {
 
    public EonHub(UUID ownerId) {
        super(ownerId, 120, "Eon Hub", Rarity.RARE, new CardType[]{CardType.ARTIFACT}, "{5}");
        this.expansionSetCode = "5DN";
        
        // Players skip their upkeep steps.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new SkipUpkeepStepEffect()));
    }
    
    public EonHub(final EonHub card) {
        super(card);
    }
    
    @Override
    public EonHub copy() {
        return new EonHub(this);
    }
    
}

class SkipUpkeepStepEffect extends ReplacementEffectImpl {
    
    public SkipUpkeepStepEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Neutral);
        staticText = "Players skip their upkeep steps";
    }
    
    public SkipUpkeepStepEffect(final SkipUpkeepStepEffect effect) {
        super(effect);
    }
    
    @Override
    public SkipUpkeepStepEffect copy() {
        return new SkipUpkeepStepEffect(this);
    }
    
    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }
    
    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        return true;
    }
    
    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.UPKEEP_STEP;
    }
    
    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        Player controller = game.getPlayer(source.getControllerId());
        return controller != null && controller.getInRange().contains(event.getPlayerId());
    }
    
}