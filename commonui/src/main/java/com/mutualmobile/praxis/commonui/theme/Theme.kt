package com.mutualmobile.praxis.commonui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColorPalette = PraxisColorPalette(
  brand = White,
  accent = PraxisColor,
  accentDark = PraxisColor,
  iconTint = Grey,
  uiBackground = Neutral0,
  uiBorder = VeryLightGrey,
  uiFloated = FunctionalGrey,
  textPrimary = TextPrimary,
  textSecondary = TextSecondary,
  textSecondaryDark = TextSecondaryDark,
  textHelp = Neutral6,
  textInteractive = Neutral0,
  textLink = Ocean11,
  iconSecondary = Neutral7,
  iconInteractive = PraxisColor,
  iconInteractiveInactive = Grey,
  error = FunctionalRed,
  progressIndicatorBg = LightGrey,
  switchColor = PraxisColor,
  statusBarColor = PraxisColor,
  isDark = false,
  searchBarBgColor = LightGrey,
  buttonColor = PraxisColor,
  buttonTextColor = White
)

private val DarkColorPalette = PraxisColorPalette(
  brand = Shadow1,
  accent = PraxisColor,
  accentDark = DarkGreen,
  iconTint = Shadow1,
  uiBackground = GreyBg,
  uiBorder = Neutral3,
  uiFloated = FunctionalDarkGrey,
  textPrimary = Shadow1,
  textSecondary = Neutral0,
  textHelp = Neutral1,
  textInteractive = Neutral7,
  textLink = Ocean2,
  iconPrimary = Neutral3,
  iconSecondary = Neutral0,
  textSecondaryDark = Neutral0,
  iconInteractive = White,
  iconInteractiveInactive = Neutral2,
  error = FunctionalRedDark,
  progressIndicatorBg = LightGrey,
  switchColor = PraxisColor,
  statusBarColor = GreyBg,
  isDark = true,
  searchBarBgColor = SearchBarDarkColor,
  buttonColor = PraxisColor,
  buttonTextColor = White

)

@Composable
fun PraxisTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (darkTheme) DarkColorPalette else LightColorPalette

  val sysUiController = LocalSysUiController.current
  SideEffect {
    sysUiController.setSystemBarsColor(
      color = colors.uiBackground.copy(alpha = AlphaNearOpaque)
    )
  }

  ProvidePraxisColors(colors) {
    MaterialTheme(
      colors = debugColors(darkTheme),
      typography = PraxisTypography,
      shapes = PraxisShapes,
      content = content
    )
  }
}

object PraxisTheme {
  val colors: PraxisColorPalette
    @Composable
    get() = LocalPraxisColor.current
}

/**
 * Praxis custom Color Palette
 */
@Stable
class PraxisColorPalette(
  brand: Color,
  accent: Color,
  accentDark: Color,
  iconTint: Color,
  uiBackground: Color,
  uiBorder: Color,
  uiFloated: Color,
  textPrimary: Color = brand,
  textSecondaryDark: Color,
  textSecondary: Color,
  textHelp: Color,
  textInteractive: Color,
  textLink: Color,
  iconPrimary: Color = brand,
  iconSecondary: Color,
  iconInteractive: Color,
  iconInteractiveInactive: Color,
  error: Color,
  notificationBadge: Color = error,
  progressIndicatorBg: Color,
  switchColor: Color,
  statusBarColor: Color,
  isDark: Boolean,
  searchBarBgColor: Color,
  buttonColor: Color,
  buttonTextColor: Color
) {
  var searchBarBg by mutableStateOf(searchBarBgColor)
    private set
  var brand by mutableStateOf(brand)
    private set
  var accent by mutableStateOf(accent)
    private set
  var accentDark by mutableStateOf(accentDark)
    private set
  var iconTint by mutableStateOf(iconTint)
    private set
  var uiBackground by mutableStateOf(uiBackground)
    private set
  var statusBarColor by mutableStateOf(statusBarColor)
    private set
  var uiBorder by mutableStateOf(uiBorder)
    private set
  var uiFloated by mutableStateOf(uiFloated)
    private set
  var textPrimary by mutableStateOf(textPrimary)
    private set
  var textSecondary by mutableStateOf(textSecondary)
    private set
  var textSecondaryDark by mutableStateOf(textSecondaryDark)
    private set
  var textHelp by mutableStateOf(textHelp)
    private set
  var textInteractive by mutableStateOf(textInteractive)
    private set
  var textLink by mutableStateOf(textLink)
    private set
  var iconPrimary by mutableStateOf(iconPrimary)
    private set
  var iconSecondary by mutableStateOf(iconSecondary)
    private set
  var iconInteractive by mutableStateOf(iconInteractive)
    private set
  var iconInteractiveInactive by mutableStateOf(iconInteractiveInactive)
    private set
  var error by mutableStateOf(error)
    private set
  var notificationBadge by mutableStateOf(notificationBadge)
    private set
  var progressIndicatorBg by mutableStateOf(progressIndicatorBg)
    private set
  var switchColor by mutableStateOf(switchColor)
    private set
  var isDark by mutableStateOf(isDark)
    private set
  var buttonColor by mutableStateOf(buttonColor)
    private set

  var buttonTextColor by mutableStateOf(buttonTextColor)
    private set


  fun update(other: PraxisColorPalette) {
    brand = other.brand
    uiBackground = other.uiBackground
    uiBorder = other.uiBorder
    uiFloated = other.uiFloated
    textPrimary = other.textPrimary
    textSecondary = other.textSecondary
    textHelp = other.textHelp
    textInteractive = other.textInteractive
    textLink = other.textLink
    iconPrimary = other.iconPrimary
    iconSecondary = other.iconSecondary
    iconInteractive = other.iconInteractive
    iconInteractiveInactive = other.iconInteractiveInactive
    error = other.error
    notificationBadge = other.notificationBadge
    switchColor = other.switchColor
    statusBarColor = other.statusBarColor
    isDark = other.isDark
    searchBarBg = other.searchBarBg
    buttonColor = other.buttonColor
    buttonTextColor = other.buttonTextColor
  }
}

@Composable
fun ProvidePraxisColors(
  colors: PraxisColorPalette,
  content: @Composable () -> Unit
) {
  val colorPalette = remember { colors }
  colorPalette.update(colors)
  CompositionLocalProvider(LocalPraxisColor provides colorPalette, content = content)
}

private val LocalPraxisColor = staticCompositionLocalOf<PraxisColorPalette> {
  error("No PraxisColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [PraxisTheme.colors].
 */
fun debugColors(
  darkTheme: Boolean,
  debugColor: Color = Color.Red
) = Colors(
  primary = debugColor,
  primaryVariant = debugColor,
  secondary = debugColor,
  secondaryVariant = debugColor,
  background = debugColor,
  surface = debugColor,
  error = debugColor,
  onPrimary = debugColor,
  onSecondary = debugColor,
  onBackground = debugColor,
  onSurface = debugColor,
  onError = debugColor,
  isLight = !darkTheme
)