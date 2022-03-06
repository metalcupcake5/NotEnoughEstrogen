package io.github.metalcupcake5.JustEnoughUpdates.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.TranslatableText;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(new TranslatableText("Just Enough Updates Config"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("features"));
            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("Enable Skyblock IDs"), ConfigManager.showSkyblockIds)
                    .setDefaultValue(true)
                    .setTooltip(new TranslatableText("Show Skyblock IDs of certain items in lore."))
                    .setSaveConsumer(newValue -> ConfigManager.showSkyblockIds = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("Enable Drop Protection"), ConfigManager.dropProtectionEnabled)
                    .setDefaultValue(true)
                    .setTooltip(new TranslatableText("Disable dropping items while in Skyblock."))
                    .setSaveConsumer(newValue -> ConfigManager.dropProtectionEnabled = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("Enable Skymall Notification"), ConfigManager.skymallNotifEnabled)
                    .setDefaultValue(true)
                    .setTooltip(new TranslatableText("Send a message and a sound when the HOTM Skymall perk has likely changed."))
                    .setSaveConsumer(newValue -> ConfigManager.skymallNotifEnabled = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("Enable Item Info"), ConfigManager.showItemInfo)
                    .setDefaultValue(true)
                    .setTooltip(new TranslatableText("Show additional info on certain items in lore, such as timestamp, uuid, and origin."))
                    .setSaveConsumer(newValue -> ConfigManager.showItemInfo = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("Show FPS"), ConfigManager.showFps)
                    .setDefaultValue(false)
                    .setTooltip(new TranslatableText("Probably gonna be removed later"))
                    .setSaveConsumer(newValue -> ConfigManager.showFps = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("Show Commisions"), ConfigManager.showCommissions)
                    .setDefaultValue(true)
                    .setTooltip(new TranslatableText("Show Dwarven Mine and Crystal Hallows commissions on screen."))
                    .setSaveConsumer(newValue -> ConfigManager.showCommissions = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("Enable Cult of the Fallen Ass Reminder"), ConfigManager.cultReminder)
                    .setDefaultValue(false)
                    .setTooltip(new TranslatableText("Remind you when the Cult of the Fallen Star meeting is approaching"))
                    .setSaveConsumer(newValue -> ConfigManager.cultReminder = newValue)
                    .build());

            general.addEntry(entryBuilder.startSelector(new TranslatableText("Cult of the Fallen Star Reminder Time"), ConfigManager.cultReminderTimeList, ConfigManager.cultReminderTime)
                    .setDefaultValue("11:30pm")
                    .setTooltip(new TranslatableText("Change the time for Cult of the Fallen Star meeting reminder"))
                    .setSaveConsumer(newValue -> ConfigManager.cultReminderTime = newValue)
                    .build());

            builder.setSavingRunnable(ConfigManager::saveConfig);

            return builder.build();
        };
    }

    /*private static class ConfigScreen extends Screen {

        private final Screen parent;
        private final TextRenderer font;
        private static final int OPTION_START = 32+13, OPTION_BUFFER = 30;
        private ConfigBuilder builder;

        protected ConfigScreen(Screen parent) {
            super(new TranslatableText("sml.config.screen.title"));
            this.parent = parent;
            this.font = MinecraftClient.getInstance().textRenderer;
        }

        @Override
        protected void init() {


            /*int buttonWidth = 75;
            int buttonX = this.width - buttonWidth - 10;
            int mid = this.width/2;

            this.addDrawableChild(new ButtonWidget(buttonX,OPTION_START,buttonWidth,20,
                    new TranslatableText(getEnableDisableLabel(ConfigManager.enabled)), (button) -> {
                ConfigManager.enabled = !ConfigManager.enabled;
                button.setMessage(new TranslatableText(getEnableDisableLabel(ConfigManager.enabled)));
            }));

            textFieldWidget = new TextFieldWidget(textRenderer, buttonX,OPTION_START + OPTION_BUFFER,buttonWidth,20, Text.of(ConfigManager.test));
            textFieldWidget.setText(ConfigManager.test);

            this.addDrawableChild(textFieldWidget);

            this.addDrawableChild(new ButtonWidget(mid - 100,this.height - 28,200,20,
                    new TranslatableText("done"), (button) -> onClose()));

        }

        private String getEnableDisableLabel(boolean value) {
            return value ? "config.custom_hud.enabled" : "config.custom_hud.disabled";
        }

        @Override
        public void onClose() {

            ConfigManager.saveConfig();
            MinecraftClient.getInstance().setScreen(parent);
        }

        @Override
        public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrix);
            if (MinecraftClient.getInstance().cameraEntity == null)
                DrawableHelper.fill(matrix,0,36,this.width,this.height - 30-9, 0x88000000);

            DrawableHelper.drawCenteredText(matrix, font, new TranslatableText("config.custom_hud.title"), this.width / 2, 13, 0xFFFFFF);
            font.draw(matrix, new TranslatableText("config.custom_hud.enable.label"), 20, OPTION_START + 5, 0xFFFFFF);
            font.draw(matrix, new TranslatableText("config.custom_hud.active_profile.label"), 20, OPTION_START + OPTION_BUFFER + 5, 0xFFFFFF);

            super.render(matrix,mouseX, mouseY, delta);
        }

    }*/
}